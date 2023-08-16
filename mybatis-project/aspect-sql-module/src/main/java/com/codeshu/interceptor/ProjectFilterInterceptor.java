package com.codeshu.interceptor;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.codeshu.aspect.ProjectFilterAspect;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;

/**
 * @author CodeShu
 * @date 2023/7/22 17:11
 */
public class ProjectFilterInterceptor implements InnerInterceptor {
	public ProjectFilterInterceptor() {
	}

	@Override
	public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
		//获取当前线程的项目过滤 SQL 语句
		String sqlFilter = ProjectFilterAspect.THREAD_LOCAL.get();
		if (StrUtil.isNotBlank(sqlFilter)) {
			//将项目过滤的 SQL 拼接到原 SQL 中
			String finalSQL = this.getSelect(boundSql.getSql(), sqlFilter);
			//替换所要执行的 SQL
			PluginUtils.mpBoundSql(boundSql).sql(finalSQL);
		}
	}

	/**
	 * 将项目过滤的 SQL 拼接到原 SQL 中
	 *
	 * @param beforeSql 原 SQL 比如 SELECT * FROM product WHERE name IS NOT NULL
	 * @param sqlFilter 项目过滤 SQL 比如(project_id in(1,2))
	 * @return 最终 SQL 比如 SELECT * FROM product WHERE name IS NOT NULL AND project_id in(1,2)
	 */
	private String getSelect(String beforeSql, String sqlFilter) {
		try {
			//获取原 SQL
			Select select = (Select) CCJSqlParserUtil.parse(beforeSql);
			PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
			//获取原 SQL 的 WHERE 语句后面的表达式 （name IS NOT NULL）
			Expression expression = plainSelect.getWhere();

			if (expression == null) {
				// WHERE 语句没有表达式，则直接拼接上去
				// 比如 SELECT * FROM product WHERE project_id in(1,2)
				plainSelect.setWhere(new StringValue(sqlFilter));
			} else {
				// WHERE 语句有表达式，则使用 AND 拼接
				// 比如 SELECT * FROM product WHERE name IS NOT NULL AND project_id in(1,2)
				AndExpression andExpression = new AndExpression(expression, new StringValue(sqlFilter));
				plainSelect.setWhere(andExpression);
			}

			//返回最终要执行的 SQL
			return select.toString().replaceAll("'", "");
		} catch (JSQLParserException e) {
			return beforeSql;
		}
	}
}
