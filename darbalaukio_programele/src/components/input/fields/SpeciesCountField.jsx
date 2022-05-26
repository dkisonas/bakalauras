import React from "react";
import InputField from "./InputField";

function SpeciesCountField({ formControl, registerName, attrName, index }) {
  return (
    <InputField
      formControl={formControl}
      registerName={registerName}
      index={index}
      attrName={attrName}
    />
  );
}

export default SpeciesCountField;
