package com.msyconseil.bokko_desktop.utils.adapterType;

import java.util.List;

public class ApiListResponse<T> {
    public boolean hasError;
    public List<T> content;
    public String errorMessage;
    public int actualPageNumber;
    public int totalNumberOfElements;
    public int totalNumberOfPages;
    public int numberOfElementByPage;
    public int numberOfFoundElement;

    // Getters et setters
    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getActualPageNumber() {
        return actualPageNumber;
    }

    public void setActualPageNumber(int actualPageNumber) {
        this.actualPageNumber = actualPageNumber;
    }

    public int getTotalNumberOfElements() {
        return totalNumberOfElements;
    }

    public void setTotalNumberOfElements(int totalNumberOfElements) {
        this.totalNumberOfElements = totalNumberOfElements;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public int getNumberOfElementByPage() {
        return numberOfElementByPage;
    }

    public void setNumberOfElementByPage(int numberOfElementByPage) {
        this.numberOfElementByPage = numberOfElementByPage;
    }

    public int getNumberOfFoundElement() {
        return numberOfFoundElement;
    }

    public void setNumberOfFoundElement(int numberOfFoundElement) {
        this.numberOfFoundElement = numberOfFoundElement;
    }
}
