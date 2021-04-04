package com.senla.store.util;

import java.io.*;

public class SerializationHandler {


    public static void writeObject(String path, Object o) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(o);
        }
    }

    public static Object readObject(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            return inputStream.readObject();
        }
    }
}
