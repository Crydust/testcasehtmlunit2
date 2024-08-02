package com.example.testcasehtmlunit2;

import org.htmlunit.BrowserVersion;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SimpleControllerTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new HtmlUnitDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void shouldDoGet() {
        driver.get("http://localhost:8080/");

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        assertThat(submittedForm, is("none"));
    }

    @Test
    void shouldHandleForm1() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form1 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        assertThat(submittedForm, is("1"));
    }

    @Test
    void shouldHandleForm2() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form2 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        assertThat(submittedForm, is("2"));
    }

    @Test
    void shouldHandleForm3() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form3 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        assertThat(submittedForm, is("3"));
    }

    @Test
    void shouldHandleForm4() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form4 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        assertThat(submittedForm, is("4"));
    }

    @Test
    void shouldHandleForm5() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form5 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        assertThat(submittedForm, is("5"));
    }

    @Test
    void shouldHandleForm6() {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form6 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("6")),
                () -> assertThat(valuesOfX, is("query, hidden, button"))
        );
    }

    @Test
    void shouldHandleForm7() {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form7 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("7")),
                () -> assertThat(valuesOfX, is("checkbox1, checkbox2, checkbox3"))
        );
    }

}
