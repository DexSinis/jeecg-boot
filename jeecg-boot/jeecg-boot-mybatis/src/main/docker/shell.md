echo "停止当前docker 镜像Container：-----------"
CID=$(docker ps | grep "yewyw-interface" | awk '{print $1}')
echo $CID
if [ "$CID" != "" ];then
  docker stop $CID
fi
echo "移除当前docker 镜像Container：-----------"
if [ "$CID" != "" ];then
  docker rm $CID
fi
echo "移除docker 镜像images：-----------"
IMG=$(docker images| grep "yewyw-interface"|awk '{print $3 }')
echo $IMG
if [ "$IMG" != "" ];then
  docker rmi $IMG
fi


mvn docker:build
echo "当前docker 镜像："
docker images | grep yewyw-interface 
echo "查找docker 镜像ID："


echo "启动容器----->"
docker run -p 9001:9001 -d yewyw-interface
echo "镜像Container：----->"
IMG=$(docker images| grep "yewyw-interface"|awk '{print $3 }')
echo $IMG
echo "镜像images：----->"
CID=$(docker ps | grep "yewyw-interface" | awk '{print $1}')
echo $CID
echo "启动服务成功！"
