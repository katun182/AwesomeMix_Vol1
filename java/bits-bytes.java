Actions actions = new Actions(driver);
WebElement mainMenu = driver.findElement(By.linkText("menulink"));
actions.moveToElement(mainMenu);

WebElement subMenu = driver.findElement(By.cssSelector("subLinklocator"));
actions.moveToElement(subMenu);
actions.click().build().perform();

driver.navigate().to("http://google.com");
Assert.assertEquals("Google", driver.getTitle());

//The following code is for strictly for running your code through a certain proxy. Simply for recording purposes.
//Uses: for ZAP and Jmeter
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
Proxy proxy = new Proxy();
proxy.setHttpProxy("localhost:8765");
cpabilities.setCapability("proxy", proxy);
ChromeOptions options = new ChromeOptions(); options.addArguments("disable-infobars");
capabilities.setCapability(ChromeOptions.CAPABILITY,options);
//System.setProperty("webdriver.gecko.driver", "./WebDrivers/geckodriver");
myWebDriver = new ChromeDriver(capabilities);


//Options and Capabilities
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
// Add the WebDriver proxy capability.
Proxy proxy = new Proxy();
proxy.setHttpProxy("myhttpproxy:3337");
capabilities.setCapability("proxy", proxy);

// Add ChromeDriver-specific capabilities through ChromeOptions.
ChromeOptions options = new ChromeOptions();
options.addExtensions(new File("/path/to/extension.crx"));
capabilities.setCapability(ChromeOptions.CAPABILITY, options);
ChromeDriver driver = new ChromeDriver(capabilities);