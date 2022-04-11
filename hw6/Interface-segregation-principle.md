# Interface segregation principle

## 定义

接口分离原则（Interface Segregation PrincipleISP）是面向对象设计原则的一种，也叫接口隔离原则。接口分离原则指在设计时采用多个与特定客户类有关的接口比采用一个通用的接口要好。即，一个类要给多个客户使用，那么可以为每个客户创建一个接口，然后这个类实现所有的接口；而不要只创建一个接口，其中包含所有客户类需要的方法，然后这个类实现这个接口。

>客户端不应该依赖它不需要实现的接口。不建立庞大臃肿的接口，应尽量细化接口，接口中的方法应该尽量少。

## 优点

1. 高内聚，低耦合
2. 可读性高，易于维护

## 举例——安全门

我们需要创建一个A品牌的安全门，该安全门具有防火、防水、防盗的功能。可以将防火，防水，防盗功能提取成一个接口，形成一套规范。

```java
using System;

namespace DesignPattern
{
    internal static class Program
    {
        public static void Main(string[] args)
        {
            var door = new ASafetyDoor();
            door.AntiTheft();
            door.FireProof();
            door.WaterProof();
        }
    }
    
    /// <summary>
    /// 防盗门接口
    /// </summary>
    public interface ISafetyDoor
    {
        /// <summary>
        /// 防盗功能
        /// </summary>
        void AntiTheft();
        
        /// <summary>
        /// 防火功能
        /// </summary>
        void FireProof();
        
        /// <summary>
        /// 防水功能
        /// </summary>
        void WaterProof();
    }

    /// <summary>
    /// A 品牌功能
    /// </summary>
    public class ASafetyDoor : ISafetyDoor
    {
        /// <summary>
        /// <inheritdoc cref="ISafetyDoor"/>
        /// </summary>
        public void AntiTheft() => Console.WriteLine("防盗");

        /// <summary>
        /// <inheritdoc cref="ISafetyDoor"/>
        /// </summary>
        public void FireProof() => Console.WriteLine("防火");

        /// <summary>
        /// <inheritdoc cref="ISafetyDoor"/>
        /// </summary>
        public void WaterProof() => Console.WriteLine("防水");
    }
}
```

上述的设计能够使得A品牌的安全门具有防盗，防水，防火的功能，能够满足最初的要求。随着市场需求的开发，发现并不是所有的安全门用户都需要安全门具有防盗，防水，防火的功能，有的仅需要其中两项，甚至一项。如果以上述的接口定义仅有防盗、防水功能的B品牌安全门，显然如果实现ISafetyDoor接口就违背了接口隔离原则。

```java
using System;

namespace DesignPattern
{
    internal static class Program
    {
        public static void Main(string[] args)
        {
            var doorA = new ASafetyDoor();
            doorA.AntiTheft();
            doorA.Fireproof();
            doorA.Waterproof();

            Console.WriteLine("============");
            var doorB = new BSafetyDoor();
            doorB.AntiTheft();
            doorB.Fireproof();
        }
    }
    
    /// <summary>
    /// 防盗接口
    /// </summary>
    public interface IAntiTheft
    {
        /// <summary>
        /// 防盗功能
        /// </summary>
        void AntiTheft();
    }

    /// <summary>
    /// 防水接口
    /// </summary>
    public interface IWaterproof
    {
        /// <summary>
        /// 防水功能
        /// </summary>
        void Waterproof();
    }

    /// <summary>
    /// 防火接口
    /// </summary>
    public interface IFireproof
    {
        /// <summary>
        /// 防火功能
        /// </summary>
        void Fireproof();
    }


    /// <summary>
    /// A 品牌安全门
    /// </summary>
    public class ASafetyDoor :  IAntiTheft, IFireproof, IWaterproof
    {
        /// <summary>
        /// <inheritdoc cref="AntiTheft"/>
        /// </summary>
        public void AntiTheft() => Console.WriteLine("防盗");

        /// <summary>
        /// <inheritdoc cref="Fireproof"/>
        /// </summary>
        public void Fireproof() => Console.WriteLine("防火");

        /// <summary>
        /// <inheritdoc cref="Waterproof"/>
        /// </summary>
        public void Waterproof() => Console.WriteLine("防水");
    }

    /// <summary>
    /// B 品牌安全门
    /// </summary>
    public class BSafetyDoor : IAntiTheft, IFireproof
    {
        /// <summary>
        /// <inheritdoc cref="AntiTheft"/>
        /// </summary>
        public void AntiTheft() => Console.WriteLine("防盗");

        /// <summary>
        /// <inheritdoc cref="Fireproof"/>
        /// </summary>
        public void Fireproof() => Console.WriteLine("防火");
    }
}
```