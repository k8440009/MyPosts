
import Table from 'react-bootstrap/Table';
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";

const Board = (props) => {
    const navigate = useNavigate();
    return (
        <div className={"div_2"}>
            <Button onClick={() => navigate('/save')}>등록</Button>
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
    );
}

export default Board;