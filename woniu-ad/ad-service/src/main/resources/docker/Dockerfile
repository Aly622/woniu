# Dockerfile 分为四部分：基础镜像信息、维护者信息、镜像操作指令、容器启动执行指令
# 基于哪个镜像
FROM java:8
# 将本地文件夹挂载到当前容器 格式为 宿主目录:容器目录， 若直接只有/tmp将会自动创建匿名的volume绑定到容器的/tmp
VOLUME /bsdata
#添加参数
ARG dockerjar
# 复制文件到容器
ADD $dockerjar /app.jar
RUN bash -c 'touch /app.jar'
#RUN apk add --no-cache tzdata
#设置变量
ENV TZ=Asia/Shanghai
ENV profileactive=""
ENV dbhost=""
ENV dbport=""
ENV dbuser=""
ENV dbpsw=""
ENV redisidx=""
ENV redishost=""
ENV redisport=""
ENV redispsw=""
ENV company=""
#声明需要暴露的端口
#EXPOSE 30003
#配置容器启动后执行的命令
ENTRYPOINT ["sh","-c","java -Xms256m -Xmx256m -jar /app.jar --spring.profiles.active=$profileactive --host.companyCode=$company --db.hostname=$dbhost --db.port=$dbport --db.username=$dbuser --db.password=$dbpsw --rds.hostname=$redishost --rds.idx=$redisidx --rds.port=$redisport --rds.password=$redispsw"]