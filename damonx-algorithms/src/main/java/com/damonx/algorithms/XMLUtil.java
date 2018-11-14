/*
 * Copyright (c) Fisher and Paykel Appliances.
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
package com.damonx.algorithms;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	// 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
	public static <T> T getBean() {
		try {
			// 创建文档对象
			final DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("config.xml"));

			// 获取包含类名的文本节点
			final NodeList nl = doc.getElementsByTagName("className");
			final Node classNode = nl.item(0).getFirstChild();
			final String cName = classNode.getNodeValue();

			// 通过类名生成实例对象并将其返回
			final Class<T> c = (Class<T>) Class.forName(cName);
			final T obj = c.newInstance();
			return obj;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
