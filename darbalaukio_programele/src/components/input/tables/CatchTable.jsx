import React from "react";
import SpeciesDropdown from "../dropdowns/SpeciesDropdown";
import Table from "./Table";
import TableDelButton from "./TableDelButton";
import SpeciesCountField from "../fields/SpeciesCountField";
import SpeciesWeightField from "../fields/SpeciesWeightField";
import DelBtnHeader from "./DelBtnHeader";
import PresentationDropdown from "../dropdowns/PresentationDropdown";
import PreservationDropdown from "../dropdowns/PreservationDropdown";
import {v4 as uuid} from "uuid";

function CatchTable({
  formControl,
  tableControl,
  registerName,
}) {
  const { fields, append, remove } = tableControl;

  const thead = (
    <thead className="bg-gray-50">
      <tr>
        <th scope="col" className="th-first">
          Species
        </th>

        <th scope="col" className="th">
          Number of individuals
        </th>

        <th scope="col" className="th">
          Weight
        </th>

        <th scope="col" className="th">
          Presentation
        </th>

        <th scope="col" className="th">
          Preservation
        </th>

        <DelBtnHeader />
      </tr>
    </thead>
  );

  const tbody = (
    <tbody className="tbody">
      {fields.map((row, index) => (
        <tr key={row.id}>
          <td className="td-first">
            <SpeciesDropdown
              formControl={formControl}
              registerName={registerName}
              attrName="species"
              index={index}
            />
          </td>
          <td className="td">
            <SpeciesCountField
              formControl={formControl}
              registerName={registerName}
              attrName="count"
              index={index}
            />
          </td>
          <td className="td">
            <SpeciesWeightField
              formControl={formControl}
              registerName={registerName}
              attrName="weight"
              index={index}
            />
          </td>
          <td className="td">
            <PresentationDropdown
              formControl={formControl}
              registerName={registerName}
              attrName="presentation"
              index={index}
            />
          </td>
          <td className="td">
            <PreservationDropdown
              formControl={formControl}
              registerName={registerName}
              attrName="preservation"
              index={index}
            />
          </td>
          <td className="table-btn-wrap">
            <TableDelButton
              remove={remove}
              index={index}
            />
          </td>
        </tr>
      ))}
    </tbody>
  );

  return (
    <Table
      name="Catch on board"
      description="Add your catch on board here"
      thead={thead}
      tbody={tbody}
      append={append}
      defaultObject={{
        identifier: uuid(),
        species: '',
        count: '',
        weight: '',
        presentation: '',
        preservation: '',
      }}
    />
  );
}

export default CatchTable;
