package step.learning.ioc;

import com.google.inject.Inject;
import step.learning.ioc.services.hash.HashService;
import step.learning.ioc.services.hash.Md5HashService;
import step.learning.ioc.services.random.RandomService;

import javax.inject.Named;

public class IocDemo {

    private final HashService digestHashService;
    private final HashService dsaHashService;
    private final RandomService randomService;

    @Inject
    public IocDemo(@Named("Digest-Hash") HashService digestHashService,@Named("DSA-Hash")  HashService dsaHashService, RandomService randomService) {
        this.digestHashService = digestHashService;
        this.dsaHashService = dsaHashService;
        this.randomService = randomService;
    }

    public void run()
    {
        System.out.println("IoC Demo");
        System.out.println(digestHashService.hash("IoC Demo"));
        System.out.println(dsaHashService.hash("IoC Demo")); // SHA-2
        System.out.println(randomService.randomHex(6));
    }
}

/*
IoC - Inversion of Control (інверсія управління)
Архітектурний патерн - шаблон побудови (організації) коду згідно з яким
управління створенням об'єктів делегується окремому модулю, який часто
називають інжектором або контейнером служб
Class1 = {dbContext = new().... } => Class1 {@Inject  dbContext ...... }
Class2 = {dbContext = new().... }  Class2 {@Inject  dbContext ...... }
Class3 = {Class2 = new().... }  Class2 {@Inject  Class2 ...... }

Через це IoC також називають DI (Dependency Injection !! не плутати з DIP
Dependency Inversion Principle)

Наявність IoC змінює підходи до організації структури у т.ч структури запуску,
поділяючи її на 2 етапи: налаштування служб(інжектора) та резолюція(Resolve)
об'єктів - побудови "дерева" залежностей і розв'язання його(які створювати
першими, які від них залежать і т.д)

Поширені системи IoC - Spring, Guice
*/