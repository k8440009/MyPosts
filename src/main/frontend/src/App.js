import {Route, Routes} from 'react-router-dom';
import Board from "./templates/Board";
import PostSave from "./templates/PostSave";
import PostUpdate from "./templates/PostUpdate";
import NotFound from "./templates/NotFound";

function App() {
    return (
      <div className={"div_1"}>
          <Routes>
              <Route path="/" element={<Board/>}></Route>
              <Route path="/save" element={<PostSave/>}></Route>
              <Route path="/update/:id" element={<PostUpdate/>}></Route>
              <Route path = "*" element={<NotFound/>}></Route>
          </Routes>
      </div>
    );
}

export default App;
