# ARM Cortex M（ARMv7&ARMv8）中断机制文档 

ARM Cortex M的中断处理是建立在其异常模式的基础上的，中断属于异常的一种。

下面借助异常模式对中断机制进行简要的介绍。

异常模式有若干个基本概念：异常类型、异常编码、异常向量表、异常处理程序、异常处理状态、异常优先级、执行优先级等。

## 异常类型

ARM Cortex M支持如下几类异常：

- 复位（reset）
- 不可屏蔽中断（NMI）
- SVC异常（SVC call）：由SVC指令引发的异常
- 调试监视异常（debug monitor）：调试会被视为一种异常
- 硬件错误（hard fault）：不属于其他异常类型的异常
  - 内存管理错误（mem manage fault）
  - 总线错误（bus fault）
  - 用例错误（usage fault）

- 中断（interrupt）：支持2种系统级中断以及最高496种外部中断
  - PendSV，多用于RTOS中
  - SysTick，定时器中断
  - 外部中断（external interrupt），通过NVIC配置的外部中断。

每个异常都有其对应的异常编码、异常优先级、异常处理程序入口地址。

详细介绍参考：

Armv7-M Architecture Reference Manual B 1.5.1 Overview of the exceptions supported;

Armv8-M Architecture Reference Manual B3.9 Exception numbers and exception priority numbers;

## 异常编码

在ARM Cortex M中每个异常都有唯一的编码（从1开始），该编码可作为偏移量，结合中断向量表的基址，定位中断处理函数。异常编码的分配如下：



编码|异常类型
---|---
1| Reset
2| NMI
3 |HardFault
4 |MemManage
5 |BusFault
6 |UsageFault
7|SecureFault (ARMV8M)
8-10| Reserved
11 |SVCall
12 |DebugMonitor
13 |Reserved
14 |PendSV
15 |SysTick
16 |External interrupt 0
...|...
16+N| External interrupt N

当系统正在执行异常处理程序时，相应的异常编码会存储在IPSR（Interrupt Program Status Register）中，可以使用`MSR`和`MRS`指令对`IPSR`进行操作，其结构如下：

![](F:\Desktop\TI开发板资料\images\xPSR.png)

ARM Cortex M最高支持496种外部中断，实际支持的外部中断数量可以通过读取ICTR（Interrupt Controller Type Register）来获取。ICTR的地址为：`0xE000E004`，其结构为：

![](F:\Desktop\TI开发板资料\images\ICTR.png)

支持的外部中断的数量为：32 * ( INTLINESNUM + 1 )

详细介绍参考：

Armv7-M Architecture Reference Manual B1.4.2 The special-purpose Program Status Registers, xPSR；

Armv7-M Architecture Reference Manual B1.5.2 Exception number definition；

Armv7-M Architecture Reference Manual B3.2.24 Interrupt Controller Type Register, ICTR;

Armv8-M Architecture Reference Manual B3.9 Exception numbers and exception priority numbers

## 异常向量表

异常向量表是由异常处理程序入口地址所组成的表，在ARM Cortex M中通过基址+偏移量来定位异常处理程序的入口地址。

基址存在于VTOR（Vector Table Offset Register）中，其地址为：`0xE00ED08`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\VTOR.png)

TBLOFF  bits[31:7] 存储着基址地址，基址地址的值与具体的架构实现有关。

例子：

reset 处理程序的入口地址为：基址+1

外部0号中断的处理程序入口地址为：基址+16

详细介绍参考：

Armv7-M Architecture Reference Manual B1.5.3 The vector table;

Armv8-M Architecture Reference Manual D1.1.23 System Control Block (NS alias);

## 异常处理状态

ARM Cortex M的所有异常中，除了reset外，只会是如下几个状态之一：

- 挂起（pending）：异常已产生并被检测到，但是还未被处理；
- 活动（active）：正在执行异常处理程序；
- 不活动（inactive）：异常既不处于挂起状态也不处于活动状态；
- 活动和挂起状态（active and pending）：只有异步异常可以处于这种状态，此时系统中某一类型的异常正在被处理，同时系统中还有同样类型的异常处于挂起状态。

可以通过ICSR（Interrupt Control and State Register）控制NMI、PendSV和SysTick异常，并查看系统当前中断状态信息。

ICSR的地址为：`0xE000ED04`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\ICSR.png)

其中常用的有，

VECTACTIVE，指示了当前正在被处理的异常的编号；

RETTOBASE，值为1说明未发生中断嵌套，否则说明发生了中断嵌套；

VECTPENDING，指示了优先级最高的处于挂起状态的异常的异常编码；

 通过SHCSR（System Handler Control and State Register）可以查看并控制系统异常（异常编号：1~15）。SHCSR的地址为：`0xE000ED24`，结构如下所示：

![](F:\Desktop\TI开发板资料\images\SHCSR.png)

其中以ENA结尾的字段可以控制是否启用对应的异常；以PENDED结尾的字段，可以查看对应异常是否处于挂起状态；以ACT结尾的字段可以用来判断相应的异常是否处于活动状态。

详细介绍参考：

Armv7-M Architecture Reference Manual B1.3.2 Exceptions；

Armv7-M Architecture Reference Manual B3.2.4 Interrupt Control and State Register, ICSR；

Armv7-M Architecture Reference Manual B3.2.13 System Handler Control and State Register, SHCSR;

Armv8-M Architecture Reference Manual B3.13 Priority model;

Armv8-M Architecture Reference Manual B3.10 Exception enable, pending, and active bits;

Armv8-M Architecture Reference Manual D1.1.23 System Control Block (NS alias);

## 异常优先级

每个异常都有其对应的优先级，大多数异常的优先级都是可以配置的。优先级使用数字来表示，其值越低则其优先级越高。

reset、NMI、hard fault的优先级是固定的（在ARMv7 M中其优先级分别为：-3、-2、-1；在ARMv8 M中分别为-4 、-2、 -3或-1）。其他异常的优先级都可以从0开始配置。

当多个异常处于挂起状态时，优先级高（对应的优先级数字低）的优先被处理；当优先级相同时，异常编号低的异常优先被处理。

异常优先级的值的范围与具体的实现有关，通常为8到256之间的一个2的整次幂。

优先级由8位二进制组成，在 45此基础上可以将优先级划分为组优先级（Group priority ）和子优先级（Subpriority），通过配置，可以将高n位设定为组优先级，其余位为子优先级。

当多个被挂起的异常有相同的组优先级时，子优先级高（对应的值低）的优先被处理。

子优先级不会影响处于活动状态的异常（不会因为子优先级高而打断当前正在处理的异常），只是在对处于挂起状态的异常进行排序时会用到。

reset、NMI、hard fault的组优先级不受配置的影响。

通过配置AIRCR（Application Interrupt and Reset Control Register），可以对组优先级进行配置。其地址为：`0xE000ED0C`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\AIRCR.png)

通过向PRIGROUP写入值（在向该寄存器写入值时，必须向VECTKEY写入`0x05FA`），可以将优先级划分为组优先级和子优先级，对应关系如下所示：

PRIGROUP值|组优先级位|子优先级位
---|---|---|
0|[7:1]|[0]
1|[7:2]|[1:0]
2|[7:3]|[2:0]
3|[7:4]|[3:0]
4|[7:5]|[4:0]
5|[7:6]|[5:0]
6|[7]|[6:0]
7|-|[7:0]

系统异常（异常编码为：1~15）的优先级可以通过SHPR（System Handler Priority Register）进行配置。SHPR共有3个，分别为：SHPR1、SHPR2、SHPR3。由于不存在0号异常，且1~3号异常（分别对应Reset、NMI、HardFault）的优先级无法修改，因此，SHPR1可以配置异常编号为4~7的优先级，SHPR2可以配置异常编号为8~11的优先级，SHPR3可以配置异常编号为12~15的优先级。

SHPR1~3的地址分别为：`0xE000ED18`、`0xE000ED1C`、`0xE000ED20`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\SHPR1.png)

![](F:\Desktop\TI开发板资料\images\SHPR2.png)

![](F:\Desktop\TI开发板资料\images\SHPR3.png)

其中，PRI_4表示异常编码为4的异常MemMange所对应的优先级，其余字段，依此类推。

详细介绍参考：

Armv7-M Architecture Reference Manual B1.5.4 Exception priorities and preemption;

Armv7-M Architecture Reference Manual B3.2.9 About the System Handler Priority Registers;

Armv8-M Architecture Reference Manual D1.1.23 System Control Block (NS alias);

Armv8-M Architecture Reference Manual B3.13 Priority model;



## NVIC

嵌套向量中断控制器NVIC（Nested Vectored Interrupt Controller），在ARM Cortex M中用于控制外部中断。通过读写NVIC的寄存器，可以对外部中断的状态和优先级进行控制。

NVIC_ISER（Interrupt Set-Enable Registers）和NVIC_ICER（Interrupt Clear-Enable Registers）寄存器组用于控制外部中断是否启用。

NVIC_ISER含有16个32位寄存器，编号为NVIC_ISER0~NVIC_ISER15，该寄存器用1位控制1个外部中断。因此NVIC_ISER共可以控制512个外部中断，但是由于ARM Cortex M只支持496个外部中断，因此，NVIC_ISER15的高16位无效。

NVIC_ISER的地址为：`0xE000E100-0xE000E13C`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_ISER.png)

在读取对应的值时，若为0，则说明对应的外部中断未被启用，若为1，则说明对应的外部中断被启用。

在写入时，写入1可**启用**对应的外部中断，写入0无效。

例子：若要启用37号外部中断（对应的异常编码为16+37=53），则需要向NVIC_ISER1的4号位（37 / 32 = 1 ... 5）写入1。

NVIC_ICER与NVIC_ISER类似，地址为：`0xE000E180-0xE000E1BC`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_ICER.png)

在读取对应的值时，若为0，则说明对应的外部中断未被启用，若为1，则说明对应的外部中断被启用。

在写入时，写入1可**停用**对应的外部中断，写入0无效。

例子：若要停用37号外部中断（对应的异常编码为16+37=53），则需要向NVIC_ISER1的4号位（37 / 32 = 1 ... 5）写入1。

NVIC_ISPR（Interrupt Set-Pending Registers）和NVIC_ICPR（Interrupt Clear-Pending Registers）寄存器组用于控制外部中断是否为挂起状态。

NVIC_ISPR与NVIC_ISER类似，其地址为：`0xE000E200-0xE000E23`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_ISPR.png)

在读取对应的值时，若为0，则说明对应的外部中断未处于挂起状态，若为1，则说明对应的外部中断处于挂起状态。

在写入时，写入1可将对应的外部中断设置为挂起状态，写入0无效。

例子：若要将37号外部中断设置为挂起状态（对应的异常编码为16+37=53），则需要向NVIC_ISPR1的4号位（37 / 32 = 1 ... 5）写入1。

NVIC_ICPR与NVIC_ISPR类似，其地址为：`0xE000E280-0xE000E2BC`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_ICPR.png)

在读取对应的值时，若为0，则说明对应的外部中断未处于挂起状态，若为1，则说明对应的外部中断处于挂起状态。

在写入时，写入1可将对应的外部中断设置从挂起状态移除，写入0无效。

例子：若要将37号外部中断设置从挂起状态移除（对应的异常编码为16+37=53），则需要向NVIC_ISPR1的4号位（37 / 32 = 1 ... 5）写入1。

NVIC_IABR（Interrupt Active Bit Registers）寄存器组用于查看外部中断是否处于活动状态。

NVIC_IABR与NVIC_ISER类似，地址为：`0xE000E300-0xE000E33C`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_IABR.png)

该寄存器为只读寄存器，在读取对应的值时，若为0，则说明对应的外部中断未处于活动状态，若为1，则说明对应的外部中断处于活动状态。

例子：若要查看37号外部中断（对应的异常编码为16+37=53）是否处于活动状态，则需要查看NVIC_IABR1的4号位（37 / 32 = 1 ... 5）。

NVIC_IPR（Interrupt Priority Registers）寄存器组用于配置外部中断的优先级。

NVIC_IPR含有124个32位寄存器，编号为NVIC_IPR0~NVIC_IPR123，该寄存器用8位控制1个外部中断的优先级。因此NVIC_IPR共可以控制496个外部中断。

NVIC_IPR的地址为：`0xE000E400-0xE000E5EC`，结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_IPR.png)

读写该寄存器可以控制对应外部中断的优先级。

例子：若要设置37号外部中断（对应的异常编码为16+37=53）的优先级，则需要向NVIC_IPR9的0-7号位（37 / 4 =9  ... 1）写入8位二进制数。

STIR（Software Triggered Interrupt Register）用于设置外部中断为挂起状态，作用与NVIC_ISPR寄存器相同，区别在于向STIP写入的是外部中断编号（外部中断对应的异常编码-16）。

STIR的地址为：`0xE000EF0`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\STIR.png)

该寄存器为只写寄存器，通过向INTID字段写入外部中断编号（外部中断对应的异常编码-16），可将对应的外部中断设置为挂起状态。

例子：若要设置37号外部中断（对应的异常编码为16+37=53）为挂起状态，则需要向STIR的INTID字段写入37（十进制）。

在ARMv8 M中，增加了NVIC_ITNS（Interrupt Target Non-secure Register）寄存器组，用于查控制外部中断是否处于安全状态。

NVIC_ITNS含有16个32位寄存器，编号为NVIC_ITNS0~NVIC_ITNS15，该寄存器用1位1个外部中断是否处于安全模式。因此NVIC_ITNS共可以控制512个外部中断，但是由于ARM Cortex M只支持496个外部中断，因此，NVIC_ITNS15的高16位无效。

NVIC_ITNS的地址为：`0xE000E380-0xE000E3BC`，其结构如下所示：

![](F:\Desktop\TI开发板资料\images\NVIC_ITNS.png)

在读取时，若为0则对应外部中断处于安全状态；否则处于不安全状态。

在写入时，写入0则对应外部中断处于安全状态；否则处于不安全状态。

例子：若要将37号外部中断设置为安全状态（对应的异常编码为16+37=53），则需要向NVIC_ITNS1的4号位（37 / 32 = 1 ... 5）写入1。

详细介绍参考：

Armv7-M Architecture Reference Manual B3.4 NVIC ;

Armv8-M Architecture Reference Manual D1.1.10 Nested Vectored Interrupt Controller

## 执行优先级

在ARM Cortex M中，当没有异常处于活动状态时，程序在运行过程中对应的优先级为（支持的最大异常优先级的值+1），即程序对应的优先级为最低级，可以被任何异常打断。

ARM Cortex M支持提高中断处理程序的优先级，这使得中断处理程序的优先级可以高于与之对应的异常（中断）本身的优先级。

例子：当中断A本身对应的优先级为3时，中断A的中断处理程序在执行过程中可以将其执行优先级提高到0，此时，若有优先级为2的中断B发生，则必须等待A的中断处理程序执行完毕，B的中断处理程序才可以执行。

通过设置PRIMASK、BASEPRI、 FAULTMASK寄存器可以修改中断处理程序的执行优先级。

以上三个寄存器的结构如下所示：

![](F:\Desktop\TI开发板资料\images\PRIMASK.png)

通过特定的指令如`MRS`、`MSR`等可以读写上述寄存器。

将PRIMASK的PM字段设置为1，可以将异常处理程序的优先级提高为0；

将FAULTMASK的FM字段设置为1，可以将异常处理程序的优先级提高为-1。但是，只有当程序优先级的值大于等于0时，才可以将FAULTMASK的FM字段设置为1；

BASEPRI寄存器的BASEPRI字段可以被程序设定为0~最大支持的优先级值；当为0时，不产生影响；否则会修改当前程序的执行优先级。但是如果修改后的优先级低于程序本身的优先级，则不对程序产生影响（不允许降低当前程序的执行优先级）。

详细介绍参考：

Armv7-M Architecture Reference Manual B1.5.4 Exception priorities and preemption;

Armv7-M Architecture Reference Manual  B1.4.3 The special-purpose mask registers;

Armv8-M Architecture Reference Manual B3.13 Priority model;

Armv8-M Architecture Reference Manual D1.1.1 Special and general-purpose registers;

### 参考资料

Armv8-M Architecture Reference Manual 

Armv7-M Architecture Reference Manual

[A Practical guide to ARM Cortex-M Exception Handling | Interrupt (memfault.com)](https://interrupt.memfault.com/blog/arm-cortex-m-exceptions-and-nvic#:~:text=The routine is usually referred to as the,exception is triggered and start executing the code.)