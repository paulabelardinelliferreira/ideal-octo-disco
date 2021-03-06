/*
 * (C) Copyright 2016 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.bonigarcia.wdm.test;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with Chrome.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class ChromeTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    /*@After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
    /*Exemplo dado em aula
    /*@Test
    public void test() {
        // Your test code here. For example:
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        By searchInput = By.id("searchInput");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys("Software");
        By searchButton = By.id("searchButton");
        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Computer software"));
    }*/
    @Test
    public void realizarLoginComSucesso(){
        driver.get("https://www.saucedemo.com/index.html");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/input[3]")).click();
        Assert.assertEquals("Products",driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/div")).getText());
    }
    @Test
    public void realizarLoginComFalha(){
        driver.get("https://www.saucedemo.com/index.html");
        driver.findElement(By.id("user-name")).sendKeys("usuario_invalido");
        driver.findElement(By.id("password")).sendKeys("senha_invalida");
        driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/input[3]")).click();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText());
    }
    @Test
    public void testarCamposObrigatoriosLogin(){
        driver.get("https://www.saucedemo.com/index.html");
        driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/input[3]")).click();
        Assert.assertEquals("Epic sadface: Username is required",driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/h3")).getText());
    }
    @Test
    public void adicionarProdutoaoCarrinho(){
        realizarLoginComSucesso();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
        Assert.assertEquals("REMOVE", driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).getText());
    }
}