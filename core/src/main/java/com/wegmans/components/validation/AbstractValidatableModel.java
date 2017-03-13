package com.wegmans.components.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidatableModel implements ValidatableModel {

    private List<String> errorMessages = new ArrayList<>();

    @Override
    public boolean isValid() {
        return errorMessages.isEmpty();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    protected void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    /**
     * Method to perform validation and check all preconditions.
     */
    protected abstract void validate();
}
