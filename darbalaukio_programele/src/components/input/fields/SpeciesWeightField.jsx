import React from "react";
import InputField from "./InputField";

function SpeciesWeightField({ formControl, registerName, attrName, index }) {
  return (
    <InputField
      formControl={formControl}
      registerName={registerName}
      attrName={attrName}
      index={index}
    />
  );
}

export default SpeciesWeightField;
