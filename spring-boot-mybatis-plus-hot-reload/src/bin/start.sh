#!/usr/bin/env bash
base_dir=$(dirname $0)/..

echo base_dir;
# loading dependency jar in lib directory
for file in $base_dir/lib/*.jar;
do
  CLASSPATH=$CLASSPATH:$file
done

if [ -z "$PUBLISH_CONFIG_OPTS" ]; then
  PUBLISH_CONFIG_OPTS="-Dpublish-config=$base_dir/config/application.yml"
fi

#应用程序启动读取配置信息文件
java_option="$PUBLISH_CONFIG_OPTS "

#应用程序启动依赖类文件路径
class_path="$CLASSPATH" 

#微服务或后台程序的运行服务名称(需修改)
service_name="test-hot-reload"

#应用程序启动入口main函数名称,不同服务的main函数名称不能相同!!!（需修改）
entry_point="com.prepared.HotReloadApplication"

$base_dir/bin/daemon.sh start "$java_option" "$class_path" "$service_name" "$entry_point"