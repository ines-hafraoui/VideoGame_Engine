# Variables
JFLAGS = -g
JC = javac
JVM = java
MAINCLASS = info3.game.Game
SRCDIR = .
BINDIR = bin

# Recherche récursive de tous les fichiers .java
SOURCES = $(shell find $(SRCDIR) -name '*.java')

# Génération des fichiers .class à partir des fichiers .java
CLASSES = $(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

# Cible par défaut
default: compile

# Règle pour compiler tous les fichiers .java
compile: $(CLASSES)

# Règle générique pour compiler les fichiers .java
$(BINDIR)/%.class: $(SRCDIR)/%.java
	@mkdir -p $(dir $@)
	$(JC) $(JFLAGS) -d $(BINDIR) $<

# Règle pour exécuter le programme principal
run: compile
	$(JVM) -cp $(BINDIR) $(MAINCLASS)

# Règle pour nettoyer les fichiers compilés
clean:
	$(RM) -r $(BINDIR)

.PHONY: compile run clean

