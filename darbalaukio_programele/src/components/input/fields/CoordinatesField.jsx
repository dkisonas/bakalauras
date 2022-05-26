import React from "react";
import CoordinateInput from "react-coordinate-input";
import ErrorMessage from "../ErrorMessage";

const CoordinatesField = ({ formControl, registerName }) => {
  const {register, setValue, getValues, formState: {errors} , clearErrors } = formControl;
  const coordinates = register(registerName, {
    validate: {
      notEmpty: (value) =>
        value !== "__° __′ __″ _ ___° __′ __″ _" ||
        "Please provide coordinates here.",
      // filled: (value) =>
      // value
    },
  });
  return (
    <>
      <CoordinateInput
        className="mt-1 w-64"
        inputRef={coordinates.ref}
        onChange={(value, { unmaskedValue, dd, dms }) => {
          setValue(registerName, value);
          setValue(`${registerName}Result`, dd);
          clearErrors(registerName);
        }}
        value={getValues(registerName)}
      />
      <ErrorMessage message={errors?.[registerName]?.message} />
    </>
  );
};

export default CoordinatesField;
