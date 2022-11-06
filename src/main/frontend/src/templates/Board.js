
import Table from 'react-bootstrap/Table';
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";
import PostSave from "./PostSave";
import {useEffect, useState} from "react";
import axios from "axios";

const Board = () => {
    const [data, setData] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('/api')
            .then(res =>
                setData(res.data))
            .catch(err => console.log(err))
    }, [])

    const tableRows = data.map(
        (item, index) =>
            <tr key={index}>
                <td>{item.id}</td>
                <td>{item.title}</td>
                <td>{item.author}</td>
            </tr>
    )

    // const tableRows = data.map(function (element, index) {
    //     <tr key={index}>
    //         <td>element.id</td>
    //         <td>element.title</td>
    //         <td>element.author</td>
    //     </tr>
    // });

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
                {tableRows}
                </tbody>
            </Table>
        </div>
    );
}

export default Board;