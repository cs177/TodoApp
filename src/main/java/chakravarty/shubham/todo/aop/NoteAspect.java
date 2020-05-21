package chakravarty.shubham.todo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
public class NoteAspect {

    public static Logger logger = LoggerFactory.getLogger(NoteAspect.class);

    //---------------------------------------------------//

    //advice -> the logic to invoke
    //POINTCUTS -> on what condition to invoke
    //JOINPOINT -> when invoking, a particular instance.

    //first wildcard is for return type
    //second wildcard (*) is there because (..) can not be the first or last element of method declaration

    //in general (*) wildcard is used to denote that any one element can take the place of *. Exactly 1.
    //it doesn't work on sub packages

    //wildcard(..) tells that there can be zero or multiple element exist in place of .. -> mainly used for accessing subpackages
    //for example chakravarty..* -> means all the subpackages of chakravarty

    //in parameter if you have (..) -> then it means it can have any number of arguments.
    //in parameter (*) denotes EXACTLY 1 parameter.

    @Pointcut("execution(* *..todo..*(..))")
    public void getMethods(){}
    //-------------------------------------------------------------------------------//

    //Advice will be invoked only before the execution of method.
    /*
    @Before("getMethods()")
    public void tryingOutAdvice(JoinPoint point){
        System.out.println("###Logging###");
        System.out.println(point.getSignature().toString());
    }*/
//-------------------------------------------------------------------------------//
    //Advice which will be invoked only if the method is returning.
/*
    @AfterReturning("getMethods()")
    public void tryingOutAdvice(JoinPoint point){
        System.out.println("###Returning###");
        System.out.println(point.getSignature().toString());
    }*/
//-------------------------------------------------------------------------------//
    //Advice which will only invoke if an error is thrown
/*
    @AfterThrowing("getMethods()")
    public void tryingOutAfterThrowingAdvice(JoinPoint point){
        System.out.println("###Throwing###");
        System.out.println(point.getSignature().toString());
    }*/
//-------------------------------------------------------------------------------//

    //Very Powerful Aspect. Can be used to decide if the internal method has to be called (proceeded) or not.
    //It takes ProceedingJoinPoint as argument and with proceed method we delegate the method to continue (just like doFilter in filter)
    //Through this we can log both Just after entering and Before exiting

    /*@Around("getMethods()")
    public void tryingOutAdvice(ProceedingJoinPoint point){
        System.out.println("###Before###");
        System.out.println(point.getSignature().toString());
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("###After###");
    }*/

    //---------------------------------------------------------------------------------------//

    //Aspect can be configured to log when a particular annotation is present on the method.
    //created @Loggable in aop package.
    //now creating a pointcut

    @Pointcut("@annotation(chakravarty.shubham.todo.aop.Loggable)")
    public void annotationLoggable(){}

    @Before("annotationLoggable()")
    public void beforeAnnotationLog(JoinPoint joinPoint){
        logger.info("Logging Before-> {}",joinPoint.toString());
        logger.info("{}",Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(value = "annotationLoggable()", returning = "result")
    public void afterReturningAnnotationLog(JoinPoint joinPoint, Object result){
        logger.info("Logging After Returning -> {}",joinPoint.toString());
        logger.info("{} returned {}",Arrays.toString(joinPoint.getArgs()), result);
    }


    @AfterThrowing(value = "annotationLoggable()")
    public void afterThrowingAnnotationLog(JoinPoint joinPoint){
        logger.info("Logging After throwing -> {}",joinPoint.toString());
        logger.info("{}",Arrays.toString(joinPoint.getArgs()));
    }

    //-----------------------------------------------------------------------------------------//

}
