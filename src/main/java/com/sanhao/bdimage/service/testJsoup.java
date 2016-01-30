package com.sanhao.bdimage.service;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class testJsoup {
	private Document doc;

	@Before
	public void before() {
		try {
			doc = Jsoup.parse(new File("output.txt"), "utf-8");

			Elements allElems = doc.getAllElements();
			System.out.println(allElems.size());
			// System.out.println(allElems.text());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void test1() {
		Elements elems = doc.select("table.table-solution-hover");

		System.out.println(elems.size());
		for (Element elem : elems) {
			System.out.println(elem.text());
			System.out.println(elem.select(".split > span").text());
		}

	}

	@Ignore
	@Test
	public void test2() {
		Elements elems = doc.select("span.top.time");

		System.out.println(elems.size());
		for (Element elem : elems) {
			System.out.println(elem.text());
		}

	}

	@Test
	public void test3() {
		Elements elems = doc.select(".solutionRow");

		System.out.println(elems.size());
		for (Element elem : elems) {
			System.out.println(elem.text());

			// System.out.println(elem.select("span.top.time").text());
			// System.out.println(elem.select("span.bottom").text());

			Elements subelems = elem.select("td");

			System.out.println("Start : " + subelems.get(0).select("span.top.time").text());
			System.out.println("Start : " + subelems.get(0).select("span.bottom").text());

			System.out.println("Arrive : " + subelems.get(1).select("span.top.time").text());
			System.out.println("Arrive : " + subelems.get(1).select("span.bottom").text());
			
			//*[@id="a_0"]/td[4]/div/div[1]/div[1]/text()
			System.out.println("Dura : " + subelems.get(2).select(".descr").text());
			System.out.println("Train : " +subelems.get(3).select(" div > div.col-xs-8.train > div.descr > img").attr("src"));
			System.out.println("Train : " +subelems.get(3).select(" div > div.col-xs-8.train > div.descr").text());
			System.out.println("Train : " + subelems.get(3).select("div > div.col-xs-8.train > div.descr > strong").text());

		}

	}
}
