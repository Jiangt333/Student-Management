#!/bin/bash
echo "starting deployment..."

#CURR_DIR=`S=\`readlink "$0"\`; [ -z "$S" ] && S=$0; dirname $S`
#echo $CURR_DIR
#
##source /etc/profile
#
#cd $CURR_DIR/../

#git pull origin master|| ! echo '[ERROR]Git pull failed!' || exit
#echo "[check point] code update successful"
#
#mvn clean package -Dmaven.test.skip=true || ! echo '[ERROR]Maven package failed！' || exit
#echo "[check point] package successful"

docker-compose -f docker-compose-env.yml down
echo "[check point] env docker down successful..."
docker-compose -f docker-compose.yml down || ! echo '[ERROR]service docker down failed！' || exit
echo "[check point] service docker down successful..."

docker rmi -f $(docker images -a | grep -v "mysql")
echo "[check point] images rm successful..."

docker-compose -f docker-compose-env.yml build || ! echo '[ERROR]env image build failed！' || exit
echo "[check point] env image build successful"
docker-compose -f docker-compose.yml build || ! echo '[ERROR]service image build failed！' || exit
echo "[check point] service image build successful"

#sudo systemctl restart docker
#echo "[check point] restart docker successful"

docker-compose -f docker-compose-env.yml up -d || ! echo '[ERROR]env docker starting failed！' || exit
echo "[check point] env docker starting up..."
docker-compose -f docker-compose.yml up -d || ! echo '[ERROR]service docker starting failed！' || exit
echo "[check point] service docker starting up..."

echo "[check point] Congratulations! startup successful!"
