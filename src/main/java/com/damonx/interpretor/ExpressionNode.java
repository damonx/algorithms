package com.damonx.interpretor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ExpressionNode implements Node {
	private final List<Node> list = new ArrayList<>();

	@Override
	public void interpret(final Context context) {
		while (true) {
			if (StringUtils.isBlank(context.currentToken())) {
				break;
			} else if (context.currentToken().equals("END")) {
				context.skipToken("END");
				break;
			} else {
				final Node commandNode = new CommandNode();
				commandNode.interpret(context);
				this.list.add(commandNode);
			}
		}

	}

	@Override
	public void execute() {
		this.list.stream().forEach(e -> e.execute());
	}

}
