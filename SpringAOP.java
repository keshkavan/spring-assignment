@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.nymisha.SpringAOP” })
class SpringContextAOP {
}

@Aspect
@Component
class MyAspect {
	@Before("execution(* com.nymisha.SpringAOP.HiByeService.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.print("Before ");
		System.out.print(joinPoint.getSignature().getName() + " called with ");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "execution(* com.nymisha.SpringAOP.*(..))”, returning = "result")
	public void after(JoinPoint joinPoint, Object result) {
		System.out.print("After ");
		System.out.print(joinPoint.getSignature().getName());
		System.out.println(" result is " + result);
	}

@Around("execution(* com.nymisha.SpringAOP.HiByeService.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.print("Around ");
        System.out.println("in around before " + joinPoint);
        if (runAround) {
            joinPoint.proceed();
        }
        System.out.println("in around after " + joinPoint);
    }


}
@Component
class HiByeService {
	public void sayHi(String name) {
		System.out.println("Hi " + name);
	}

	public void sayBye() {
		System.out.println("Bye");
	}

	public String returnSomething() {
		return "Hi Bye";
	}
}
