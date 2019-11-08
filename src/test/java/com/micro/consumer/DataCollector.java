package com.micro.consumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class DataCollector {

	public static void main(String[] args) throws Throwable {
		String commaPath = "/Users/zhou/Desktop/data collector/input_files/comma.txt";
		String pipePath = "/Users/zhou/Desktop/data collector/input_files/pipe.txt";
		String spacePath = "/Users/zhou/Desktop/data collector/input_files/space.txt";
		
		File commaFile = new File(commaPath);
		File pipeFile = new File(pipePath);
		File spaceFile = new File(spacePath);
		
		if(!commaFile.isFile() || !commaFile.exists()) {
			throw new Throwable("can't find the file,confirm the path is correct.");
		}
		
		if(!pipeFile.isFile() || !pipeFile.exists()) {
			new Throwable("can't find the file,confirm the path is correct.");
		}
		
		if(!spaceFile.isFile() || !spaceFile.exists()) {
			new Throwable("can't find the file,confirm the path is correct.");
		}
		
		List<Person> list = handleFileLine(commaFile,",",1);
		
		list.addAll(handleFileLine(pipeFile,"\\|",2));
		list.addAll(handleFileLine(spaceFile," ",3));
		
		System.out.println("Output 1:");
		list = list.stream().sorted(Comparator.comparing(Person::getSex).thenComparing(Person::getName)).collect(Collectors.toList());
		for (Person person : list) {
			System.out.println(person.getName()+" "+person.getSex()+" "+person.getBirthDay()+" "+person.getColor());
		}
		
		System.out.println("\nOutput 2:");
		list = list.stream().sorted(Comparator.comparing(Person::getBirthDaySort).thenComparing(Person::getName)).collect(Collectors.toList());
		for (Person person : list) {
			System.out.println(person.getName()+" "+person.getSex()+" "+person.getBirthDay()+" "+person.getColor());
		}
		
		System.out.println("\nOutput 3:");
		list = list.stream().sorted((a, b) -> b.getName().compareTo(a.getName())).collect(Collectors.toList());
		for (Person person : list) {
			System.out.println(person.getName()+" "+person.getSex()+" "+person.getBirthDay()+" "+person.getColor());
		}
		
		
	}

	private static List<Person> handleFileLine(File file,String split,int type) {
		List<Person> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {
                //System.out.println(s);
                list.add(strToObject(s,split,type));
            }
            br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static Person strToObject(String s, String split, int type) throws ParseException {
		String[] strs;
        Person p = new Person();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        switch(type) {
            case 1:
            	strs = s.replaceAll(" ", "").split(split);
            	p.setName(strs[0]+" "+strs[1]);
                p.setSex(strs[2]);
                p.setBirthDaySort(format.parse(strs[4]));
                p.setBirthDay(strs[4]);
                p.setColor(strs[3]);
            	break;
            case 2:
            	strs = s.replaceAll(" ", "").split(split);
            	p.setName(strs[0]+" "+strs[1]);
                p.setSex(strs[3].equals("M")?"Male":"Female");
                p.setBirthDaySort(format.parse(strs[5].replace("-", "/")));
                p.setBirthDay(strs[5].replace("-", "/"));
                p.setColor(strs[4]);
            	break;
            case 3:
            	strs = s.split(split);
            	p.setName(strs[0]+" "+strs[1]);
                p.setSex(strs[3].equals("M")?"Male":"Female");
                p.setBirthDaySort(format.parse(strs[4].replace("-", "/")));
                p.setBirthDay(strs[4].replace("-", "/"));
                p.setColor(strs[5]);
            	break;
            default:
            	
        }
        return p;
	}
	
	static class Person{
		String name;
		String sex;
		String color;
		Date birthDaySort;
		String birthDay;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public Date getBirthDaySort() {
			return birthDaySort;
		}
		public void setBirthDaySort(Date birthDaySort) {
			this.birthDaySort = birthDaySort;
		}
		public String getBirthDay() {
			return birthDay;
		}
		public void setBirthDay(String birthDay) {
			this.birthDay = birthDay;
		}
		
	}
}
