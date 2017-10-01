                                        #个人笔记： http://www.liaoxuefeng.com/wiki/
                                        ##Python 基础
#数据类型和变量：
如果在字符串中有许多的字符需要转义，就需要很多的'\'，为了简化可以使用 r'' 表示如下：
print(r'i am here \n')结果：i am here \n


#Python 属于动态语言；而Java属于静态语言
Java中：int a = "hello"
Python中:a = 'hello' or a = "hello"


#在Python中，通常用全部大写的变量名表示常量：PI = 3.14159265359
注意：Python根本没有任何机制保证PI不会被改变，也就是说我们可以改变PI这个静态变量值。但是在java中是不能的。


#在Python 中有两种除法
10/3 = 3.3333333333333335 and 9/3 = 3.0
10//3 = 3


#使用list（列表）和tuple（元组）
list(使用[])是一个可变的有序表且ta里面的元素的数据类型可以不同
paper = ['i', 'love', 'you']
book = ['android', 'ios', paper, 'phone']
可看成一个二维数组，类似的还有三维、四维……数组;获取：'love' = book[2][1]
tuple(使用())和 list 非常类似，但是 tuple 一旦初始化就不能修改.它也没有 append() ， insert() 这样的方法。
其他获取元素的方法和list是一样的.因为tuple不可变，所以代码更安全。
如果可能，能用tuple代替list就尽量用tuple。
最后来看一个“可变的”tuple：t = ('a', 'b', ['c', 'd'])  t[2][0] = 'x' t[2][1] = 'y'  --> t = ('s', 'b', ['x', 'y'])
tuple 所谓的“不变”是说，tuple 的每个元素，指向永远不变。即指向'a'，就不能改成指向'b'，
指向一个 list，就不能改成指向其他对象，但指向的这个 list 本身是可变的！


#条件判断
Python 与 Java 区别上：Python代码块使用'缩进'；Python使用'冒号'，不使用'分号'作为语句结尾:
age =  20
if age >  18:
    print('your age is',age)
条件语句结构：
if <条件判断1>:
    <执行1>
elif <条件判断2>:
    <执行2>
elif <条件判断3>:
    <执行3>
else:
    <执行4>
if 判断条件还可以简写：
if x:
    print('True')
只要'x'是非零数值、非空字符串、非空list等，就判断为True


#循环
Python的循环有两种，一种是for...in循环，依次把list或tuple中的每个元素迭代出来。
names = ['Michael', 'Bob', 'Tracy']
for name in names:
    print(name)
or
for i in range(3):
    print(names[i])

第二种循环是while循环，只要条件满足，就不断循环，条件不满足时退出循环。
sum = 0
n = 99
while n > 0:
    sum += n
    n -= 1


#使用dict(字典dictionary，其他语言中也称为map)和set
请务必注意，dict内部存放的顺序和key放入的顺序是没有关系的。dict的key必须是不可变对象.
dict内部存放的顺序和key放入的顺序是没有关系的。通过key计算位置的算法称为哈希算法.
Michael = ['name':Michael, 'age':25, 'sex':man](使用{})
在Python字典中，key是唯一且不能重复的。添加元素的方式也可这样：
dic = {'name':laowang, 'age':18}
dic['sex'] = 女
>>>{'name':laowang, 'age':18, 'sex':女}
set和dict类似，是一组key的集合，但不存储value。由于key不能重复，所以，在set中，没有重复的key。(set中使用{})
set = ([1,2,2,3,4])
set
{1,2,3,4}
不但自动过滤，而且要创建一个set必须使用一个list作为输入集合。



                                    ##函数
#定义函数
在Python中，定义一个函数要使用def语句，依次写出函数名、括号、括号中的参数和冒号:，
然后，在缩进块中编写函数体，函数的返回值用return语句返回。
def my_abs(x):
    if x >= 0:
        return x
    elif x < 0:
        return -x

定义一个空函数：
def nullMath():
    pass
'pass'用来作占位符，如果还没想好怎么写函数的代码，可以先放一个pass，让代码能运行起来。

定义一个能对参数类型进行检查的函数：
def my_abs(x):
    if not isinstance(x, (int , float))
        raise TypeError('bad operand type')
    if x >= 0:
        return x
    if x < 0:
        return -x
定义函数时，需要确定函数名和参数个数；
如果有必要，可以先对参数的数据类型做检查；
函数体内部可以用return随时返回函数结果；
函数执行完毕也没有return语句时，自动return None。
函数可以同时返回多个值，但其实就是一个 tuple。


#函数的参数
def power(x, n):
    if not isinstance(x, (int, float))
        raise TypeError('计算类型错误')
    sum = 1
    while n > 0:
        sum = sum * x
        n --
    return sum

上面的函数必须传入二个参数才能正确运行；如果想传入一个也ok，就要这样修改（改做 默认参数类型的函数）：
def power(x, n = 2):
    if not isinstance(x, (int, float))
        raise TypeError('计算类型错误')
    sum = 1
    while n > 0:
        sum = sum * x
        n --
    return sum
这个时候调用power(5)等同于调用power(5, 2)
使用默认参数有什么好处？最大的好处是能降低调用函数的难度。
当函数有多个参数时，把(经常)变化大的参数放前面，(偶尔)变化小的参数放后面。变化小的参数就可以作为默认参数。
当不按顺序提供部分默认参数时，需要把参数名写上。比如调用enroll('Adam', 'M', city='Tianjin')
def add_end(L = []):
    L.append('END')
    return L
>>> add_end()
['END', 'END']
>>> add_end()
['END', 'END', 'END']
很多初学者很疑惑，默认参数是[]，但是函数似乎每次都“记住了”上次添加了'END'后的list。
原因解释如下：
Python函数在定义的时候，默认参数L的值就被计算出来了，即[]，因为默认参数L也是一个变量，
它指向对象[]，每次调用该函数，如果改变了L的内容，则下次调用时，默认参数的内容就变了，不再是函数定义时的[]了。
所以，定义默认参数要牢记一点：'默认参数必须指向不变对象！'比如：元组、str、None
修改列子：
def add_end(L = []):
    if L is None:
        L = []
    L.append('END')
    return L
定义一个可变参数，参数前面加了一个*号：
def calc(*num):
    sum = 0
    for n in num:
        sum += n*n
    return sum
or
nums = [1,2,3]
calc(*nums)
命名关键字参数：
需要在函数内部通过kw检查。
def person(name, age, **kw):
    if 'city' in kw:
        pass
    if 'job' in kw:
        pass
    print('....')
调用者仍然可以传入不受限制的关键字参数,比如：
person('jack', name, 'age', ''other, kw)
和关键字参数"**kw"不同，命名关键字参数"*"需要一个特殊分隔符*，*后面的参数被视为命名关键字参数；
命名关键字参数必须传入参数名，这和位置参数不同。如果没有传入参数名，调用将报错.
def person(name, age, *, city, job):
    print(name, age, city, job)
调用方式：
person('jack', 24, city = '北京', job = 'teacher')
在Python中定义函数，可以用必选参数、默认参数、可变参数、关键字参数和命名关键字参数，
这5种参数都可以组合使用。但是请注意，参数定义的顺序必须是：必选参数、默认参数、
可变参数、命名关键字参数和关键字参数。
def f1(a, b, c=8, *args, **kw):
    print(...)


#切片
使用切片的方式：一个列表 list['bag', 'look', 'eye', 'on', 'sight', 'jack', 'bob']
现在去前 三个元素列表：list[0:3] or list[:3]
Python支持L[-1]取倒数第一个元素，那么它同样支持倒数切片:list[-2:]  >>>list['jack', 'bob']
还有这种方式，取出前10个元素，然后每隔2个取一个：list[:10:2]，取出所有元素，每隔5个取出一个元素：list[::5]
tuple 也是一种 list ，唯一的区别是tuple不可变。tuple 的切片操作是(1,2,3,4,5,6,7)[0:4]   >>>(1,2,3,4)
字符串也可以看做是一个切片操作, str 的切片操作是：'hell world'[0:3]   >>>'hel'

#迭代
如果给一个 list 或者 tuple 我们可以使用 for 循环进行遍历。我们称之为 迭代
>>>M['a','b','c','d','e','f','g']
>>>for m in M:
    print("迭代打印：", m)
>>>W{'book':'山海经', 'name':'老徐', 'slave':'老董'}
>>>for w in W.values()  for w in W.items()
另外，如何判断一个对象是可迭代的对象？
>>>from collections import Iterable
>>>isinstance('abc', Iterable) #判断 str 是否是一个可迭代的对象 返回值是boolean
>>>isinstance([12,26,780], Iterable) #判断 list 是否是一个可迭代的对象 返回值是boolean
#同样 tuple 整数 等也可以使用这种方式进行判断
#最后一个小问题，如果要对list实现类似Java那样的下标循环怎么办？Python内置的enumerate函数可以把一个list变成索引-元素对
>>>for key, value in enumerate(['h', 'e', 'l'])
>>>0  h
>>>1  e
>>>2  l
#上面的for循环里，同时引用了两个变量，在Python里是很常见的，比如下面的代码：
>>>for x,y in [(1,4), (2,5), (6,9)]
>>>1 4
>>>2 5
>>>369


#列表生成式
列表生成式就是 list Comprehensions ,是Python内置的非常简单却强大的可以用来创建 list 的生成式
比如我们要生成这样的：[1,2,3,4,5,6,7] 使用：list[range(1, 8)]
比如我们要生成这样的：[1, 4, 9, 16, 25] 使用：[x * x for x in range(1, 6)]
而且 for 循环后面可以添加 if 判断
比如我们要筛选出偶数的平方：[x * x for x in range(1, 11) if x % 2 == 0]  >>> [4, 16, 64]
还可以使用两层循环实现全排列：[m+n for m in 'XYZ' for n in 'ABC']  >>>['XA','XB','XC','YA','YB','YC','ZA','ZB','ZC']
并且列表生成式还可以使用两个或者更多的变量来实现：
>>> M{'a':'l1', 'b':'l2', 'c':'l3','d':'l4','e':'l5'}
>>>for x,y in M.items()
        print(x,'=',y)
>>>['a=l1', 'b=l2', 'c=l3', 'd=l4', 'e=l5']



#生成器 generator
#迭代器
可以直接使用for循环的数据类型有：
一类是集合数据类型：比如 list, tuple, dict, set, str等
一类是generator,包括生成器和带 yield 的生成器函数 generator function
这些可直接使用 for 循环的都可以作为迭代对象 Iterable 不是 Iterator
如果要把 Iterable 变成 Iterator 要使用函数方法 iter()进行转化


#高阶函数
所谓的高阶函数：
def add(a, b, method):
    sum = method(a) + method(b)
    return sum
add(-10, 20, abs)(sum = abs(-10) + abs(20)   >>>30)

因为变量可以指向函数：method = abs   method(-10) == abs(-10)
#map/reduce 内置的高阶函数
Python内建了map()和reduce()函数；Map函数接收两个参数，一个参数是函数另一个参数是Iterable。并且函数会作用到序列上。
>>>def f(x):
    return x*x
>>>L = map(f, [1,2,3,4,5,6])
>>>list(L)
>>>[1,4,9,16,25,36]
在上面的结果上，L是一个Iterator即惰性序列。一次可以使用 list()函数 返回出一个list；还可以计算任意复杂的函数，比如：
>>>list(map(str, [1,2,30]))
>>>['1','2','30']
看一下 reduce
>>>reduce(f(x), [x1, x2, x3]) --->>f(f(x1, x2), x3)
reduce 是把结果和序列的下一个元素做累积计算，如一个序列求和
from functools import reduce
def sum(a,b):
    return a+b
reduce(sum, [1,2,3,4,5,6])
看一下 filter
Python内建的 filter()函数，用于过滤序列；和 map 不同的是 filter 把传入的函数依次作用每个元素，通过返回值 True 还是 False
决定保留还是丢弃该单元
def is_odd(o):
    return o % 2 = 1

list(filter(is_odd, [1,2,3,4,5,6,7]))
#result：[1,3,5,7]
看一下 sorted
sorted 函数也是一个高阶函数，它可以接收一个 key 函数来实现自定义的排序;
key指定的函数将作用于list的每一个元素上，并根据key函数返回的结果进行排序。
sorted([1,-8,0,5,-2], key = abs)
#结果：[0,1,-2,5,-8]
再比如进行反向排序，不改动key函数，可以传入第三个参数 reverse = True
sorted(['bob', 'about', 'Zoo', 'Credit'], key=str.lower, reverse=True)
#结果：['Zoo', 'Credit', 'bob', 'about']



#函数作为返回值(又被称作'闭包')
##高阶函数出来可以接受函数作为参数外，还可以把函数作为结果值返回。
def lazy_sum(*arg):
    def sum():
        ax = 0
        for e in arg:
            ax += e
        return ax
    return sum
#result:f = lazy_sum(1,2,3,4,5,6)
#result:f()
#result:21

fall in love at first sight.

        













