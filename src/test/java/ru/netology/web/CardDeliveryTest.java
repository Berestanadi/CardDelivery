package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    LocalDate currentDate = LocalDate.now().plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formattedCurrentDate = currentDate.format(formatter);

    @Test
    void HappyPathTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[placeholder='Дата встречи']").setValue(formattedCurrentDate);
        $("[data-test-id='name'] input").setValue("Григорьев Иван");
        $("[data-test-id=phone] input").setValue("+79990009898");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=notification]").shouldBe(appear, Duration.ofSeconds(15));
        $(".notification__title").shouldHave(exactText("Успешно!"));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + formattedCurrentDate));
    }
}
