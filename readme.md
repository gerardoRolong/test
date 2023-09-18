# Project with an endpoint to print a html file using multiple threads and other endpoint to test a custom annotation  

# How To Run  
- Clone the project and in the root directory of the project run: **docker build -t java_test .**  
- Next run: **docker run -it --rm -p 8080:8080 --name java_test_container java_test**  
- Then navigate to **localhost://8080** followed by one of the rutes named bellow  

## End point to generate the html:  
/html_generation/view

## End point to mock an api that returns a number or a 429 code error on every 2 out of 5 requests:  
/mock_api/hit_count

## End point to make a http call to another end point and handle an exponential back off in case it gets a 429 response  
/external_api/test_back_off