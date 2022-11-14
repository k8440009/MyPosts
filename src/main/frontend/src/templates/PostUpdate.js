import Button from "react-bootstrap/Button";
import React, {useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

function PostUpdate () {
    const [title, setTitle] = useState('')
    const [content, setContent] = useState('')
    const {id} = useParams();

    const navigate = useNavigate();

    const onChangeTitle = (e) => {
        setTitle(e.target.value)
    }

    const onChangeContent = (e) => {
        setContent(e.target.value)
    }

    /**
     * 수정
     * axios의 put 방식을 사용하여 글을 update한다.
     * @param e
     */
    const handleUpdate = (e) => {
        const data = {
            title : title,
            content : content
        }
        let url = "/api/v1/posts/"
        url += id

        axios.put(url, data)
            .then(function (response) {
                console.log(JSON.stringify(response))
                alert("글이 수정되었습니다.")

                navigate('/')
            })
            .catch(function (error) {
                alert(JSON.stringify(error))
            });
    }

    const handleCancel = (e) => {
        navigate('/')
    }

    const handleDelete = (e) => {
        let url = "/api/v1/posts/"
        url += id

        axios.delete(url)
            .then(function (response) {
                console.log(JSON.stringify(response))
                alert("글이 삭제되었습니다.")

                navigate('/')
            })
            .catch(function (error) {
                alert(JSON.stringify(error))
            })
    }

    return (
        <div className="container-sm">
            <div>
                <form>
                    <div className="form-group">
                        <label htmlFor="title">제목</label>
                        <input onChange={onChangeTitle} value={title} type="text" className="form-control" id="title" placeholder="제목을 입력하세요"/>
                    </div>
                    <div>
                        <label htmlFor="content"> 내용 </label>
                        <textarea onChange={onChangeContent} value={content} className="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
                    </div>
                </form>
            </div>
            <Button onClick={handleUpdate}>수정완료</Button>
            <Button onClick={handleCancel} variant="secondary">취소</Button>
            <Button onClick={handleDelete} variant="danger">삭제</Button>
        </div>
    );
}

export default PostUpdate;
