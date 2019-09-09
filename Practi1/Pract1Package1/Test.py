'''
Created on 05-Sep-2019

@author: E000639
'''
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
user = "srinivas.mudapaka"
pwd = "Pravallika@1981"
driver = webdriver.Chrome(executable_path='C:\Chrome Driver\chromedriver.exe')
driver.get("https://www.gmail.com")
assert "Gmail" in driver.title
elem = driver.find_element_by_id("identifierId")
elem.send_keys(user)
driver.find_element_by_id("identifierNext").click()
driver.implicitly_wait(200)
#elem = driver.find_element_by_xpath("//input[@type='password']");
driver.find_element_by_name("password").send_keys(pwd)
driver.implicitly_wait(200)
driver.find_element_by_id("passwordNext").click()
driver.implicitly_wait(200)
# elem.send_keys(Keys.RETURN)
driver.close() 


  
# set webdriver path here it may vary 
