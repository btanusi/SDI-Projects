#Ceph's Citchen: SDI Project 2

##Github:
https://github.com/Cephs-Kitchen

###Development Notes:

connecting to docker database:
#build image from database repo
docker build -t database .
docker run --name database1 -p 8002:5432 db
#in another terminal:
psql -U ceph -h localhost -p 8002 cephs_citchen
password: ceph

docker build -t btanusi/cephs-pantrylist .
docker build -t btanusi/cephs-pantrylist-api .

