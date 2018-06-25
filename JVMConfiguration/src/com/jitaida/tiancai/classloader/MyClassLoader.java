package com.jitaida.tiancai.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader {

        private String name;
        private String path="";

        private final String fileType = ".class";

        public MyClassLoader(String name){
            super();

            this.name = name;
        }

        public MyClassLoader(ClassLoader parent, String name){
            super(parent);//显示制定当前类加载器的父加载器是什么

            this.name = name;
        }

        @Override
        public String toString(){
            return this.name;
        }

        public void setPath(String path){
            this.path = path;
        }


        private byte[] loadClassData(String name) {
            InputStream is = null;
            byte[] data = null;
            ByteArrayOutputStream baos = null;

            try {
                this.name = this.name.replace(".", "/");
                is = new FileInputStream(new File(path + this.name + this.fileType));

                baos = new ByteArrayOutputStream();

                int ch = 0;

                while(-1 != (ch = is.read())){
                    baos.write(ch);
                }

                data = baos.toByteArray();

            }catch(Exception e){

            }finally {
                try {
                    is.close();
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return data;
        }

        @Override
        public Class<?> findClass(String name){
            byte[] data = this.loadClassData(name);
            return this.defineClass(name, data, 0 , data.length);
        }

        public static void main(String [] args){
            MyClassLoader loader1 = new MyClassLoader("loader1");
            loader1.setPath("/home/pauls/test1/");

            MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
            loader1.setPath("/home/pauls/test2/");

            MyClassLoader loader3 = new MyClassLoader(null, "loader3");
            loader1.setPath("/home/pauls/test3/");

            test(loader2);
            test(loader3);
        }

        private static void test (ClassLoader l){
            try {
                Class clazz= l.loadClass("dog");
                clazz.newInstance();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
}
