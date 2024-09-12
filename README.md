# One-Layer Neural Network for Text Language Classification

This project implements a one-layer neural network that classifies texts based on their language.

## Key Features

### Text Normalization
All texts are first normalized by:
- Removing any non-English characters.
- Converting all uppercase letters to lowercase.
- Counting the frequency of each letter in the text.

### Input Representation
After normalization, each text is represented as a 26-dimensional vector (one dimension for each letter of the English alphabet). 
Additionally, these vectors are **normalized** to ensure that input values are consistent and comparable. 

#### Vector Normalization:
The normalization process scales the vector that its length becomes 1.
This is done by first calculating the vector’s length (using the square root of the sum of squares of its components) and then dividing each component of the vector by this length.

### Neural Network Architecture
The neural network uses a **local architecture**, meaning that each language has its own perceptron (neuron), trained to activate for texts in that language.

### Languages Supported
The available languages for classification are:
- Polish
- German
- French

## Training and Testing
- For each language, at least 3-5 example texts are provided for training.
- The network learns to classify each language based on the letter frequency patterns found in the text.

## Interfaces

### Manual Testing
The program provides a keyboard interface where users can input test sentences. The network will classify the language of the input based on the trained perceptrons.

### File-based Interface
Users can also provide a text file, and the program will analyze the text and return the predicted language.

### Additional Features
- The program outputs a list of all supported languages, along with the probability that the given text is written in each language.
- If multiple perceptrons are activated, the program removes the activation function and compares the raw outputs (dot product values) to determine which perceptron was the most strongly activated.
