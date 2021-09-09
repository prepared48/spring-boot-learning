参考文档：

1、Introduction to Spring Data Redis：https://www.baeldung.com/spring-data-redis-tutorial

2、Spring Boot 2.X(六)：Spring Boot 集成 Redis：https://juejin.cn/post/6844903962504609805

知识点：

1、spring-boot-starter-data-redis 包含了 jedis 和 Lettuce。

2、Jedis 在实现上是直连 redis server，多线程环境下非线程安全，除非使用连接池，为每个 redis实例增加物理连接。

3、Lettuce 是 一种可伸缩，线程安全，完全非阻塞的Redis客户端，多个线程可以共享一个RedisConnection,它利用Netty NIO 框架来高效地管理多个连接，从而提供了异步和同步数据访问方式，用于构建非阻塞的反应性应用程序。


问题：

>Lettuce is now used instead of Jedis as the Redis driver when you use spring-boot-starter-data-redis. If you are using higher level Spring Data constructs you should find that the change is transparent.
We still support Jedis. Switch dependencies if you prefer Jedis by excluding io.lettuce:lettuce-core and adding redis.clients:jedis instead.
Connection pooling is optional and, if you are using it, you now need to add commons-pool2 yourself as Lettuce, contrary to Jedis, does not bring it transitively.

https://www.cnblogs.com/award/p/11442636.html