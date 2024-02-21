FROM gitpod/workspace-full

USER gitpod

# Install Java
RUN sudo apt-get update && \
    sudo apt-get install -y openjdk-11-jdk

# Install Maven
RUN sudo apt-get install -y maven

# Install browsers for Selenium testing
RUN sudo apt-get install -y firefox-esr && \
    sudo apt-get install -y chromium-browser

# Verify installations
RUN java -version
RUN mvn -v
RUN firefox --version
RUN chromium --version

# Clean up
RUN sudo apt-get clean && \
    sudo rm -rf /var/lib/apt/lists/*
