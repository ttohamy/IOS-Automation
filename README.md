
# 📱 iOS Automation Framework

A robust and modular automation framework tailored for iOS applications, leveraging Appium and Java. This framework facilitates efficient UI testing, encompassing interactions with alerts, sliders, and other UI components.

---

## 🚀 Features

- **Alert Handling**: Seamlessly interact with iOS alerts, including those with multiple options.
- **Slider Interactions**: Programmatically manipulate sliders with precision.
- **Dynamic Element Location**: Utilize strategies akin to Selenium for locating elements via parent-child hierarchies.
- **Custom Utilities**: Incorporates utility functions for common actions like text input, waiting for elements, and more.
- **Modular Design**: Structured for scalability and ease of maintenance.

---

## 🛠️ Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- Appium Server
- Xcode with iOS Simulator or a physical iOS device
- Node.js and npm (for Appium)

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/ttohamy/IOS-Automation.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd IOS-Automation
   ```

3. **Install Dependencies**

   Ensure all Maven dependencies are installed:

   ```bash
   mvn clean install
   ```

4. **Start Appium Server**

   ```bash
   appium
   ```

---

## 📂 Project Structure

```
IOS-Automation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── automation/
│   │               ├── base/           # Base classes and utilities
│   │               ├── pages/          # Page Object Models
│   │               └── tests/          # Test cases
├── pom.xml                            # Maven configuration file
└── README.md                          # Project documentation
```

---

## 🧪 Usage

### Running Tests

Execute tests using Maven:

```bash
mvn test
```

### Sample Test Case

```java
@Test
public void testSliderInteraction() {
    IOSDriver driver = initializeDriver();
    By sliderLocator = By.id("slider");
    sliding(driver, "right", 10, 80, sliderLocator);
    // Add assertions as needed
}
```

---

## 🧰 Utility Functions

### `sliding`

Simulates a sliding action on a specified element.

**Parameters:**

- `driver`: Instance of `IOSDriver`.
- `slidingDirection`: Direction of the slide (`"left"` or `"right"`).
- `startPointPercentage`: Starting point as a percentage of the element's width.
- `endPointPercentage`: Ending point as a percentage of the element's width.
- `locator`: Locator for the target element.

**Usage:**

```java
sliding(driver, "right", 10, 80, By.id("slider"));
```

---

## 🧩 Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 📬 Contact

For questions or support, please open an issue on the [GitHub repository](https://github.com/ttohamy/IOS-Automation/issues).
