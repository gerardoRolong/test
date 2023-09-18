# Project with an endpoint to print a html file using multiple threads and other endpoint to test a custom annotation  

## End point to generate the html:  
/html_generation/view

## End point to mock an api that returns a number or a 429 code error on every 2 out of 5 requests:  
/mock_api/hit_count

## End point to make a http call to another end point and handle an exponential back off in case it gets a 429 response  
/external_api/test_back_off