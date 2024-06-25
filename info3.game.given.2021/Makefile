# Variables
SRC_DIR := .
BIN_DIR := bin
LIBS := resources/json-20240303.jar:resources/json-simple-1.1.1.jar:parserGal.jar
CLASSPATH := $(LIBS)

# Compilateur et options
JAVAC := javac
JAVA := java
JAVAC_FLAGS := -d $(BIN_DIR) -classpath $(CLASSPATH) --release 11

# Récupérer tous les fichiers source .java
SRC_FILES := $(shell find $(SRC_DIR) -name '*.java')

# Cibles
all: $(BIN_DIR)/classes

# Règle pour compiler les fichiers .java en .class
$(BIN_DIR)/classes: $(SRC_FILES)
	@mkdir -p $(BIN_DIR)
	$(JAVAC) $(JAVAC_FLAGS) $(SRC_FILES)

clean:
	rm -rf $(BIN_DIR)


run: all
	$(JAVA) -cp $(BIN_DIR):$(CLASSPATH) info3/game/Game

.PHONY: all clean run
