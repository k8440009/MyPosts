import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";

import Table from 'react-bootstrap/Table'

function App() {
  const [hello, setHello] = useState('')

  useEffect(() => {
    axios.get('/api/hello')
        .then(res => setHello(res.data))
        .catch(err => console.log(err));
  });
  return (
      <div className={"div_1"}>
          <div className={"div_2"}>
              <Table striped bordered hover>
                  <thead className={"thead_1"}>
                  <tr className={"tr_1"}>
                      <th>게시글번호</th>
                      <th>제목</th>
                      <th>작성자</th>
                      <th>최종수정일</th>
                  </tr>
                  </thead>
                  <tbody>
                    <tr>
                        <td>1</td>
                        <td>hello</td>
                        <td>김애용</td>
                        <td>20221105</td>
                    </tr>
                  </tbody>
              </Table>
          </div>
      </div>
  );
}

export default App;
