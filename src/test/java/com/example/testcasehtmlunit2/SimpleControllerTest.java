package com.example.testcasehtmlunit2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

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
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form7 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("7")),
                () -> assertThat(valuesOfX, is("checkbox1, checkbox2, checkbox3"))
        );
    }

    @Test
    void shouldHandleForm8() {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form8 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("8")),
                // WARNING: the order is different from "shouldHandleForm6"
                // ... but only when running this test with HtmlUnit
                // ... when submitting this form with firefox or chrome the output is "query, hidden, button"
                () -> assertThat(valuesOfX, is("query, hidden, button"))
        );
    }

    @Test
    void shouldHandleForm9() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form9 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("9")),
                () -> assertThat(valuesOfX, is("checkbox1, checkbox2, checkbox3"))
        );
    }

    @Test
    void shouldHandleForm10(@TempDir Path tempDir) throws IOException {
        driver.get("http://localhost:8080/");

        Path tempFile = tempDir.resolve("example.txt");
        Files.writeString(tempFile, "Hello world!", StandardCharsets.US_ASCII);
        driver.findElement(By.cssSelector("#form10 input[name='file']")).sendKeys(tempFile.toAbsolutePath().toString());

        driver.findElement(By.cssSelector("#form10 button")).click();

        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String fileName = driver.findElement(By.cssSelector("#fileName")).getText();
        String fileContents = driver.findElement(By.cssSelector("#fileContents")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("10")),
                () -> assertThat(fileName, is("example.txt")),
                () -> assertThat(fileContents, is("Hello world!"))
        );
    }

    @Test
    void shouldHandleForm11() {
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form11 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("11")),
                () -> assertThat(valuesOfX, is("query, body"))
        );
    }

    @Test
    void shouldHandleForm12() {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form12 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("12")),
                () -> assertThat(valuesOfX, is("query, body"))
        );
    }

    @Test
    void shouldHandleForm13() {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form13 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("13")),
                () -> assertThat(valuesOfX, is("query, body"))
        );
    }

    @Test
    void shouldHandleForm14() {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        driver.findElement(By.cssSelector("#form14 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("14")),
                // WARNING: options method ignores body parameters
                () -> assertThat(valuesOfX, is("query"))
        );
    }

    @Test
    void shouldHandleForm15(@TempDir Path tempDir) throws IOException {
        driver.get("http://localhost:8080/");

        Path tempFile = tempDir.resolve("example.txt");
        Files.writeString(tempFile, "Hello world!", StandardCharsets.US_ASCII);
        driver.findElement(By.cssSelector("#form15 input[name='file']")).sendKeys(tempFile.toAbsolutePath().toString());

        driver.findElement(By.cssSelector("#form15 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        String fileName = driver.findElement(By.cssSelector("#fileName")).getText();
        String fileContents = driver.findElement(By.cssSelector("#fileContents")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("15")),
                // WARNING: options method ignores body parameters
                () -> assertThat(valuesOfX, is("body, query")),
                () -> assertThat(fileName, is("example.txt")),
                () -> assertThat(fileContents, is("Hello world!"))
        );
    }

    @Test
    void shouldHandleForm16(@TempDir Path tempDir) throws IOException {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        Path tempFile = tempDir.resolve("example.txt");
        Files.writeString(tempFile, "Hello world!", StandardCharsets.US_ASCII);
        driver.findElement(By.cssSelector("#form16 input[name='file']")).sendKeys(tempFile.toAbsolutePath().toString());

        driver.findElement(By.cssSelector("#form16 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        String fileName = driver.findElement(By.cssSelector("#fileName")).getText();
        String fileContents = driver.findElement(By.cssSelector("#fileContents")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("16")),
                () -> assertThat(valuesOfX, is("body, query")),
                () -> assertThat(fileName, is("example.txt")),
                () -> assertThat(fileContents, is("Hello world!"))
        );
    }

    @Test
    void shouldHandleForm17(@TempDir Path tempDir) throws IOException {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        Path tempFile = tempDir.resolve("example.txt");
        Files.writeString(tempFile, "Hello world!", StandardCharsets.US_ASCII);
        driver.findElement(By.cssSelector("#form17 input[name='file']")).sendKeys(tempFile.toAbsolutePath().toString());

        driver.findElement(By.cssSelector("#form17 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        String fileName = driver.findElement(By.cssSelector("#fileName")).getText();
        String fileContents = driver.findElement(By.cssSelector("#fileContents")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("17")),
                () -> assertThat(valuesOfX, is("query, body")),
                () -> assertThat(fileName, is("example.txt")),
                () -> assertThat(fileContents, is("Hello world!"))
        );
    }


    @Test
    void shouldHandleForm18(@TempDir Path tempDir) throws IOException {
        // fails with HtmlUnitRequestBuilder
        driver.get("http://localhost:8080/");

        Path tempFile = tempDir.resolve("example.txt");
        Files.writeString(tempFile, "Hello world!", StandardCharsets.US_ASCII);
        driver.findElement(By.cssSelector("#form18 input[name='file']")).sendKeys(tempFile.toAbsolutePath().toString());

        driver.findElement(By.cssSelector("#form18 button")).click();

        waitUntilAjaxFinished();
        String submittedForm = driver.findElement(By.cssSelector("#submittedForm")).getText();
        String valuesOfX = driver.findElement(By.cssSelector("#valuesOfX")).getText();
        String fileName = driver.findElement(By.cssSelector("#fileName")).getText();
        String fileContents = driver.findElement(By.cssSelector("#fileContents")).getText();
        assertAll(
                () -> assertThat(submittedForm, is("18")),
                () -> assertThat(valuesOfX, is("body, query")),
                () -> assertThat(fileName, is("example.txt")),
                () -> assertThat(fileContents, is("Hello world!"))
        );
    }

    private void waitUntilAjaxFinished() {
        new WebDriverWait(driver, Duration.of(2, SECONDS))
                .until(visibilityOfElementLocated(By.id("fileContents")));
    }

}
