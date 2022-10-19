package com.wigo.voc.sys.aop;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {
//	private static final String AOP_TRANSACTION_METHOD_NAME = "*";
	private static final String AOP_TRANSCTION_EXPRESSION = "execution(* com.wigo..service.*Impl.*(..))";
	private static final String CRM_AOP_TRANSCTION_EXPRESSION = "execution(* com.wigo.crm..*Service.*(..))";
	private static int TX_TIMEOUT = 600;
	@Resource(name = "txManager")
	private TransactionManager txManager;

//	@Qualifier("transactionManager")
//	private TransactionManager transactionManager;
//
//	@Qualifier("crmTransactionManager")
//	private TransactionManager crmTransactionManager;

	@Bean
	public TransactionInterceptor transactionAdvice() {
		return advice(txManager);
	}

	@Bean
	public Advisor transactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_TRANSCTION_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}

	@Bean
	public TransactionInterceptor crmTransactionAdvice() {
		return advice(txManager);
	}

	@Bean
	public Advisor crmTransactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(CRM_AOP_TRANSCTION_EXPRESSION);

		return new DefaultPointcutAdvisor(pointcut, crmTransactionAdvice());
	}

	private TransactionInterceptor advice(TransactionManager txManager) {
		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		DefaultTransactionAttribute readOnlyTransactionAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyTransactionAttribute.setReadOnly(true);
		readOnlyTransactionAttribute.setTimeout(TX_TIMEOUT);

		RuleBasedTransactionAttribute writeTransactionAttribute = new RuleBasedTransactionAttribute();
		writeTransactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		writeTransactionAttribute.setTimeout(TX_TIMEOUT);

		Map<String, TransactionAttribute> txAttribute = new HashedMap<String, TransactionAttribute>();

		txAttribute.put("get*", readOnlyTransactionAttribute);
		txAttribute.put("select*", readOnlyTransactionAttribute);
		txAttribute.put("insert*", writeTransactionAttribute);
		txAttribute.put("update*", writeTransactionAttribute);
		txAttribute.put("delete*", writeTransactionAttribute);
		txAttribute.put("save*", writeTransactionAttribute);
		source.setNameMap(txAttribute);
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(txManager);
		transactionInterceptor.setTransactionAttributeSource(source);
		return transactionInterceptor;
	}
}
