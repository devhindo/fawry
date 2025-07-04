# E-Commerce System Makefile

# Variables
JAVA_SRC_DIR = src/main/java
BIN_DIR = bin
MAIN_CLASS = com.ecommerce.Main
PACKAGE_PATH = com/ecommerce

# Default target
.PHONY: all
all: compile run

# Create bin directory if it doesn't exist
$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Compile Java source files
.PHONY: compile
compile: $(BIN_DIR)
	@echo "Compiling Java source files..."
	@if command -v javac >/dev/null 2>&1; then \
		javac -d $(BIN_DIR) $(JAVA_SRC_DIR)/$(PACKAGE_PATH)/Main.java $(JAVA_SRC_DIR)/$(PACKAGE_PATH)/**/*.java && \
		echo "Compilation completed successfully!"; \
	else \
		echo "ERROR: javac not found!"; \
		echo "Please install JDK:"; \
		echo "  Ubuntu/Debian: sudo apt install default-jdk"; \
		echo "  CentOS/RHEL: sudo yum install java-devel"; \
		echo "  macOS: brew install openjdk"; \
		exit 1; \
	fi

# Run the application
.PHONY: run
run: compile
	@echo "Running E-Commerce System..."
	@echo "=================================="
	@if command -v java >/dev/null 2>&1; then \
		java -cp $(BIN_DIR) $(MAIN_CLASS); \
	else \
		echo "ERROR: java not found!"; \
		echo "Please install Java Runtime Environment (JRE)"; \
		exit 1; \
	fi

# Clean compiled files
.PHONY: clean
clean:
	@echo "Cleaning compiled files..."
	rm -rf $(BIN_DIR)
	@echo "Clean completed!"

# Check Java environment
.PHONY: check-java
check-java:
	@echo "Checking Java environment..."
	@echo "Java version:"
	@if command -v java >/dev/null 2>&1; then \
		java -version; \
	else \
		echo "  ❌ Java not found"; \
	fi
	@echo ""
	@echo "Java compiler (javac):"
	@if command -v javac >/dev/null 2>&1; then \
		javac -version; \
		echo "  ✅ Ready to compile"; \
	else \
		echo "  ❌ javac not found"; \
		echo "  Install JDK: sudo apt install default-jdk"; \
	fi

# Test compilation only (useful for CI/CD)
.PHONY: test-compile
test-compile: compile
	@echo "Test compilation passed!"

# Show help
.PHONY: help
help:
	@echo "Available targets:"
	@echo "  all         - Compile and run the application (default)"
	@echo "  compile     - Compile Java source files"
	@echo "  run         - Compile and run the application"
	@echo "  clean       - Remove compiled files"
	@echo "  check-java  - Check Java environment setup"
	@echo "  test-compile- Test compilation only"
	@echo "  help        - Show this help message"
	@echo ""
	@echo "Usage examples:"
	@echo "  make        - Compile and run"
	@echo "  make run    - Compile and run"
	@echo "  make clean  - Clean compiled files"
	@echo "  make check-java - Verify Java setup"

# Force rebuild (clean then compile and run)
.PHONY: rebuild
rebuild: clean all
