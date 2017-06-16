Actions actions = new Actions(driver);
WebElement mainMenu = driver.findElement(By.linkText("menulink"));
actions.moveToElement(mainMenu);

WebElement subMenu = driver.findElement(By.cssSelector("subLinklocator"));
actions.moveToElement(subMenu);
actions.click().build().perform();

driver.navigate().to("http://google.com");
Assert.assertEquals("Google", driver.getTitle());
