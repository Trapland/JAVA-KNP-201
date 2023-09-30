package step.learning.oop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // інформація доступна при запуску (включається у виконавчий код)
@Target(ElementType.TYPE) // елементи, що позначаються даною анотацією ( тип - класи)
public @interface Serializable { // для позначння Літератури, яка включається у файл
}
/*
Анотації - різновиди інтерфейсів( та їх реалізації)
Як правило використовуються для метаданих(доддаткові, супровідні дані,
які не впливають на самі дані, але їх доповнюють
 */