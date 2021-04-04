package com.senla.store.util;

import java.time.LocalDateTime;
import java.util.Random;

public class MyRandom {
    private static final Random random = new Random();
    private static final LocalDateTime localDateTime = LocalDateTime.now();


    public static LocalDateTime getDateOfLastSale() {
        // расброс год
        return localDateTime.minusSeconds(random.nextInt(31536000));
    }

    public static int getPublicationYear() {
        //1900-2021 гг
        return 1900 + random.nextInt(121);
    }

    public static int getPriceOfBook() {
        return random.nextInt(50);
    }

    public static LocalDateTime getDateOrder() {
        // минутная разбежка для наглядности сортировки
        return localDateTime.plusSeconds(random.nextInt(60));
    }

    public static LocalDateTime getDateChangedOrder() {
        // разбежка 1-2 мин для наглядности сортировки
        return localDateTime.plusSeconds(60 + random.nextInt(60));
    }
}
