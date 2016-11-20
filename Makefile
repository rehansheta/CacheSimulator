JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Cache.java \
        CacheSimulator.java \
        CacheStat.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
