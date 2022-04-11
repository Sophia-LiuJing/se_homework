# Open-closed principle

## 定义

开放封闭原则（OCP，Open Closed Principle）是所有面向对象原则的核心。软件设计本身所追求的目标就是封装变化、降低耦合，而开放封闭原则正是对这一目标的最直接体现。其他的设计原则，很多时候是为实现这一目标服务的，例如以Liskov替换原则实现最佳的、正确的继承层次，就能保证不会违反开放封闭原则。

## 特性

1. 它们 "面向扩展开放"。

也就是说模块的行为是能够被扩展的。当应用程序的需求变化时，我们可以使模块表现出全新的或与以往不同的行为，以满足新的需求。

2. 它们 "面向修改封闭"。

模块的源代码是不能被侵犯的，任何人都不允许修改已有源代码。

## 作用

1. 对软件测试的影响
   
软件遵守开闭原则的话，软件测试时只需要对扩展的代码进行测试就可以了，因为原有的测试代码仍然能够正常运行。

2. 可以提高代码的可复用性

粒度越小，被复用的可能性就越大；在面向对象的程序设计中，根据原子和抽象编程可以提高代码的可复用性。

3. 可以提高软件的可维护性
   
遵守开闭原则的软件，其稳定性高和延续性强，从而易于扩展和维护。

## 使用开闭原则

1. 抽象约束

第一，通过接口或者抽象类约束扩展，对扩展进行边界限定，不允许出现在接口或抽象类中不存在的public方法；

第二，参数类型、引用对象尽量使用接口或者抽象类，而不是实现类；

第三，抽象层尽量保持稳定，一旦确定即不允许修改。

2. 元数据控制模块行为

元数据就是用来描述环境和数据的数据，通俗地说就是配置参数，参数可以从文件中获得，也可以从数据库中获得。

Spring容器就是一个典型的元数据控制模块行为的例子，其中达到极致的就是控制反转（Inversion of Control）

3. 制定项目章程

在一个团队中，建立项目章程是非常重要的，因为章程中指定了所有人员都必须遵守的约定，对项目来说，约定优于配置。

4. 封装变化

对变化的封装包含两层含义：

第一，将相同的变化封装到一个接口或者抽象类中；

第二，将不同的变化封装到不同的接口或抽象类中，不应该有两个不同的变化出现在同一个接口或抽象类中。

## 案例

需求: 有圆形, 有椭圆形, 根据要求画出相应的形状

```java
public class GraphicEditor {

    public void draw(Shape shape) {
        if (shape.m_type == 1) {
            drawRectangle();
        } else if(shape.m_type == 2) {
            drawCircle();
        }
    }

    public void drawRectangle() {
        System.out.println("画长方形");
    }

    public void drawCircle() {
        System.out.println("画圆形");
    }

    class Shape {
        int m_type;
    }

    class Rectangle extends Shape {
        Rectangle() {
            super.m_type=1;
        }
    }

    class Circle extends Shape {
        Circle() {
            super.m_type=2;
        }
    }

}
```

如果增加三角形， 首先, 要增加一个三角形的类, 继承自Shape；其次, 要增加一个画三角形的方法drawTrriage()；然后, 在draw方法中增加一种类型type=3的处理方案。

这就违背了开闭原则-对扩展开发, 对修改关闭. 增加一个类型, 修改了三处代码.

```java
public class GraphicEditor1 {

    public void draw(Shape shape) {
        shape.draw();
    }



    interface Shape {
        void draw();
    }

    class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("画矩形");
        }
    }

    class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("画圆形");
        }
    }

}
```

各种类型的形状自己规范自己的行为, 而GraphicEditor.draw()只负责画出来.

当增加一种类型三角形. 只需要增加一个三角形的类,实现Shape接口以及调用draw方法,划出来就可以了.