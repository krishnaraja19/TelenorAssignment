# Flexible way search record from database using spring boot,H2 and JPA
Please follow the below procedure
#Step 1:
Extract product-docker.tar file. Please run below command.
docker image ls
Step 2: Please check, product image file is available or not.
Step 3: If it is available, Please run below command.
docker container run -p 8080:8080 product

Step 4: You can take below url and browse the url in browser. Then it will provide the result.
http://localhost:8080/api/product?type=phone
http://localhost:8080/api/product?type=subscription
http://localhost:8080/api/product?type=
http://localhost:8080/api/product
http://localhost:8080/api/product?type=phone&properties=color:rosa
http://localhost:8080/api/product?type=subscription&properties=gb_limit:50
http://localhost:8080/api/product?type=subscription&properties=
http://localhost:8080/api/product?type=phone&properties=color:rosa&min_price=270&max_price=500
http://localhost:8080/api/product?type=phone&properties=color:rosa&min_price=&max_price=
http://localhost:8080/api/product?type=phone&properties=color:rosa&min_price
http://localhost:8080/api/product?type=phone&properties=color:rosa&min_price=100&max_price=1000&city=Malm%C3%83%C2%B6
http://localhost:8080/api/product?type=phone&properties=color:rosa&min_price=100&max_price=1000&city=
