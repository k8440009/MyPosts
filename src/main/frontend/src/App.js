import {useEffect, useState} from "react";
import axios from "axios";

import {BrowserRouter, Route, Routes, useNavigate} from 'react-router-dom';
import Board from "./templates/Board";
import PostSave from "./templates/PostSave";
import Button from "react-bootstrap/Button";

function App() {
    return (
      <div className={"div_1"}>
          <Routes>
              <Route path="/" element={<Board/>}></Route>
              <Route path="/save" element={<PostSave/>}></Route>
          </Routes>
      </div>
    );
}

export default App;
