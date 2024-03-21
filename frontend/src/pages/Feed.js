import {
  Box,
  Card,
  Grid,
  TextField,
  Typography,
  InputAdornment,
  Button,
} from '@mui/material';
import { useEffect, useState } from 'react';
import SearchIcon from '@mui/icons-material/Search';
import { Link } from 'react-router-dom';
import postService from '../api/postService';

const Feed = () => {
  const [query, setQuery] = useState('');
  const [posts, setPosts] = useState([]);

  //
  useEffect(() => {
    if (query.length === 0) setPosts(postService.getPosts);
  }, [query]);

  const handleChange = (e) => {
    setQuery(e.target.value);
    postService.getPostsByQuery(query);
  };

  return (
    <Grid container spacing={2} sx={{ margin: '2%' }}>
      <Grid item xs={12} sx={12} md={12} lg={12}>
        <Button sx={{ margin: '1% 2%' }} variant="outlined">
          <Link to="/">Home</Link>
        </Button>
        <Box>
          <TextField
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
            placeholder="Search..."
            sx={{ width: '75%', padding: '2% auto' }}
            fullWidth
            onChange={handleChange}
          />
        </Box>
      </Grid>
      {posts &&
        posts.map((p) => {
          return (
            <Grid key={p.id} item xs={12} md={6} lg={4}>
              <Card sx={{ padding: '3%', overflow: 'hidden', width: '84%' }}>
                <Typography
                  variant="h5"
                  sx={{ fontSize: '2rem', fontWeight: '600' }}
                >
                  {p.profile}
                </Typography>
                <Typography
                  sx={{ color: '#585858', marginTop: '2%' }}
                  variant="body"
                >
                  Description: {p.desc}
                </Typography>
                <br />
                <br />
                <Typography variant="h6">
                  Years of Experience: {p.exp} years
                </Typography>

                <Typography gutterBottom variant="body">
                  Skills :{' '}
                </Typography>
                {p.techs.map((s, i) => {
                  return (
                    <Typography variant="body" gutterBottom key={i}>
                      {s} .{` `}
                    </Typography>
                  );
                })}
              </Card>
            </Grid>
          );
        })}
    </Grid>
  );
};

export default Feed;
