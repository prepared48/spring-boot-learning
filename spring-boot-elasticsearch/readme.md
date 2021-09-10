https://www.baeldung.com/spring-data-elasticsearch-tutorial

通过docker，启动单实例 es

```
docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
```