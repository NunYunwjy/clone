# CopyTo指南

作者：厦门大学信息学院2022级林韦帆

旨在寻找一种方法。避免po中import bo以及在bo中import vo或者dto从而不破坏层次体系结构。以及不用为自动生成的po手动添加注解

## 基本原理

新增创建注解CopyTo
通过类似依赖反转的思想,将po中import bo改为bo中import po以及在bo中import vo或者dto改为dto和vo中import bo，从而不破坏层次体系结构。
同时自然也不用在po中手动添加注解。

```java
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface CopyTo {
   Class<?>[] value();
}
```


## 示例

```java
@AllArgsConstructor
@NoArgsConstructor
public class TestA {
    Integer x;
    Integer y;

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }
}
```

```java
@AllArgsConstructor
@NoArgsConstructor
public class TestB {
    Integer x;
    Integer z;

    public Integer getX() {
        return x;
    }

    public Integer getZ() {
        return z;
    }
}
```

```java
@AllArgsConstructor
@NoArgsConstructor
@CopyTo({ TestA.class, TestB.class })
public class TestC {
    Integer y;
    Integer z;

    public void setY(Integer y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
```

生成的CloneFactory:

```java
public final class CloneFactory {
   /**
    * Copy all fields from source to target
    * @param target the target object
    * @param source the source object
    * @return the copied target object
    */
   public static TestA copy(TestA target, TestC source) {
      target.setY(source.getY());
      return target;
   }

   /**
    * Copy all fields from source to target
    * @param target the target object
    * @param source the source object
    * @return the copied target object
    */
   public static TestB copy(TestB target, TestC source) {
      target.setZ(source.getZ());
      return target;
   }
}
```

拷贝对象：

```java
TestA a = new TestA(1, 2);
TestC c = new TestC(3, 4);
CloneFactory.copy(a, c);  // 将a的内容拷贝到c
assert a.getX() == 1;
assert a.getY() == 3;
```

克隆对象：
```java
TestB c = new TestB(1, 2);
TestC b = CloneFactory.copy(new TestB(), c);  // copy方法会将拷贝后的结果返回
assert b.getX() == null;
assert b.getZ() == 2;
```



## @CopyTo.Exclude
采用@CopyTo.Exclude注解写在get方法前面，有此注解的属性不copy
由于目前已经兼容了lombok注解所以注解也可以写在字段上，有此注解的属性不copy

```java
import jdk.jfr.DataAmount;

@NoArgsConstructor
@ToString(callSuper = true, doNotUseGetters = true)
@CopyFrom({ProductDraftVo.class, ProductDraftPo.class})
@CopyTo({ProductDraftVo.class})
public class ProductDraft extends OOMallObject implements Serializable {
   @CopyFrom.Exclude({ProductDraftVo.class})
   public void getCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   @CopyTo.Exclude({ProductDraftVo.class})
   private Long id;
   
}
```
在这个例子中，ProductDraft的id属性不会被copy到ProductDraft中。
ProductDraft的categoryId属性不会被copy到ProductDraftVo中。
